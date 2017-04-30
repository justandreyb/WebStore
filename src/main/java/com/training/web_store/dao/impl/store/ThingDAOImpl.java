package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.ThingDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.ArgumentExchanger;
import com.training.web_store.util.database.DBConnector;
import com.training.web_store.util.exception.StorageException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class ThingDAOImpl implements ThingDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String THING_TABLE = "thing";
    private static final String RATING_TABLE = "rating_to_customer";
    private static final String ID = "id";
    private static final String THING_NAME = "name";
    private static final String THING_CATEGORY = "category_id";
    private static final String THING_DESCRIPTION = "description";
    private static final String THING_CREATION_DATE = "creation_date";
    private static final String THING_REVIEW = "review";
    private static final String CATEGORY_NAME = "category";
    private static final String THING_BRAND = "brand";
    private static final String THING_BRAND_ID = "brand_id";
    private static final String THING_IS_AVAILABLE = "is_available";
    private static final String RATING_THING = "thing_id";
    private static final String RATING_USER = "customer_id";
    private static final String RATING_VALUE = "value";

    private static final String ADD_THING_QUERY =
            "INSERT INTO " + DATABASE + "." + THING_TABLE + " (" +
                THING_NAME + ", " +
                THING_CATEGORY + ", " +
                THING_DESCRIPTION + ", " +
                THING_CREATION_DATE + ", " +
                THING_BRAND_ID +
            ") " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_THING_QUERY = "{call getThing(?)}";

    private static final String GET_THINGS_QUERY = "{call getThings()}";

    private static final String GET_THINGS_FOR_PRODUCT_QUERY = "{call getThingsForProduct(?)}";

    private static final String UPDATE_THING_QUERY =
            "UPDATE " + DATABASE + "." + THING_TABLE +
            " SET " +
                THING_NAME + "=?, " +
                THING_CATEGORY + "=?, " +
                THING_DESCRIPTION + "=?, " +
                THING_CREATION_DATE + "=?, " +
                THING_BRAND_ID + "=?" +
            " WHERE " +
                ID + "=?"
            ;

    private static final String ADD_RATING_QUERY =
            "INSERT INTO " + DATABASE + "." + RATING_TABLE + "( " +
                RATING_THING +
                RATING_USER +
                RATING_VALUE +
            ") " +
            "VALUES (?, ?, ?)";

    private static final String DELETE_RATING_QUERY =
            "DELETE FROM " + DATABASE + "." + RATING_TABLE +
            " WHERE " +
                RATING_THING + "=?" +
                RATING_USER + "=?";

    private static final String GET_RATING_QUERY =
            "SELECT " +
                RATING_VALUE +
            " FROM " + DATABASE + "." + RATING_TABLE +
            " WHERE " +
                RATING_THING + "=?";

    private static final String ADD_REVIEW_QUERY =
            "UPDATE " + DATABASE + "." + THING_TABLE +
            " SET " +
                THING_REVIEW + "=?" +
            " WHERE " +
                ID + "=?";

    private static final String DELETE_REVIEW_QUERY =
            "UPDATE " + DATABASE + "." + THING_TABLE +
            " SET " +
                THING_REVIEW + "=NULL" +
            " WHERE " +
                ID + "=?";

    private static final String GET_REVIEW_QUERY =
            "SELECT " +
                THING_REVIEW +
            " FROM " + DATABASE + "." + THING_TABLE +
            " WHERE " +
                ID + "=?";

    private static final String SET_THING_AVAILABLE =
            "UPDATE " + DATABASE + "." + THING_TABLE +
            " SET " +
                THING_IS_AVAILABLE + "=?" +
            " WHERE " +
                ID + "=?";

    private static final String ERROR_ADDING = "Error during adding new entity";
    private static final String ERROR_DELETING = "Error during deleting";

    @Override
    public void addThing(Thing thing, int categoryId, int brandId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_THING_QUERY);

            statement.setString(1, thing.getName());
            statement.setInt(2, categoryId);
            statement.setString(3, thing.getDescription());
            statement.setDate(4, (Date) thing.getCreationDate());
            statement.setInt(5, brandId);

            statement.executeUpdate();

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_ADDING);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public Thing getThing(int thingId) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        Thing thing = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_THING_QUERY);

            statement.setInt(1, thingId);

            set = statement.executeQuery();

            while (set.next()) {
                thing = new Thing();

                thing.setId(thingId);

                String name = set.getString(THING_NAME);
                Date creationDate = set.getDate(THING_CREATION_DATE);
                String category = set.getString(CATEGORY_NAME);
                String brand = set.getString(THING_BRAND);
                String description = set.getString(THING_DESCRIPTION);

                thing.setName(name);
                thing.setCategory(category);
                thing.setBrand(brand);
                thing.setCreationDate(creationDate);
                thing.setDescription(description);
            }
            return thing;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Thing> getThings() throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Thing> things = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_THINGS_QUERY);

            set = statement.executeQuery();

            things = new LinkedList<Thing>();

            while (set.next()) {
                Thing thing = new Thing();

                int thingId = set.getInt(ID);
                String name = set.getString(THING_NAME);
                Date creationDateSQL = set.getDate(THING_CREATION_DATE);
                String category = set.getString(CATEGORY_NAME);
                String brand = set.getString(THING_BRAND);
                String description = set.getString(THING_DESCRIPTION);

                thing.setId(thingId);
                thing.setName(name);
                thing.setCategory(category);
                thing.setBrand(brand);
                java.util.Date creationDate = ArgumentExchanger.exchangeFromSQLDate(creationDateSQL);
                thing.setCreationDate(creationDate);
                thing.setDescription(description);

                things.add(thing);
            }
            return things;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Thing> getThingsForProduct(int productId) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Thing> things = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_THINGS_FOR_PRODUCT_QUERY);

            statement.setInt(1, productId);

            set = statement.executeQuery();

            things = new LinkedList<Thing>();

            while (set.next()) {
                Thing thing = new Thing();

                int thingId = set.getInt(ID);
                String name = set.getString(THING_NAME);
                Date creationDateSQL = set.getDate(THING_CREATION_DATE);
                String category = set.getString(CATEGORY_NAME);
                String brand = set.getString(THING_BRAND);
                String description = set.getString(THING_DESCRIPTION);

                thing.setId(thingId);
                thing.setName(name);
                thing.setCategory(category);
                thing.setBrand(brand);
                java.util.Date creationDate = ArgumentExchanger.exchangeFromSQLDate(creationDateSQL);
                thing.setCreationDate(creationDate);
                thing.setDescription(description);

                things.add(thing);
            }
            return things;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void updateThing(int thingId, String name, String description, Date creationDate, int categoryId, int brandId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE_THING_QUERY);

            statement.setInt(6, thingId);
            statement.setString(1, name);
            statement.setInt(2, categoryId);
            statement.setString(3, description);
            statement.setDate(4, creationDate);
            statement.setInt(5, brandId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Something went wrong. Thing wasn't updated");
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public void setThingAvailable(int thingId, boolean available) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_THING_AVAILABLE);
            statement.setBoolean(1, available);
            statement.setInt(2, thingId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_DELETING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void addRating(int userId, int thingId, byte value) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_RATING_QUERY);
            statement.setInt(1, userId);
            statement.setInt(2, thingId);
            statement.setByte(3, value);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_ADDING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public byte getRating(int thingId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_RATING_QUERY);
            statement.setInt(1, thingId);

            set = statement.executeQuery();
            int amount = 0;
            int result = 0;

            while (set.next()) {
                result += set.getByte(RATING_VALUE);
                amount++;
            }

            return (byte) (result / amount);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void deleteRating(int thingId, int userId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DELETE_RATING_QUERY);
            statement.setInt(1, thingId);
            statement.setInt(2, userId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_DELETING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void addThingReview(int thingId, String review) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_REVIEW_QUERY);
            statement.setString(1, review);
            statement.setInt(2, thingId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_ADDING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public String getThingReview(int thingId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_REVIEW_QUERY);
            statement.setInt(1, thingId);

            set = statement.executeQuery();
            String review = null;

            while (set.next()) {
                review = set.getString(THING_REVIEW);
            }

            return review;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void deleteThingReview(int thingId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DELETE_REVIEW_QUERY);
            statement.setInt(1, thingId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_DELETING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }
}
