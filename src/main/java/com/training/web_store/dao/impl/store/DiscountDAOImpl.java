package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Discount;
import com.training.web_store.dao.DiscountDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.ArgumentExchanger;
import com.training.web_store.util.database.DBConnector;
import com.training.web_store.util.exception.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class DiscountDAOImpl implements DiscountDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String DISCOUNT_TABLE = "discount";
    private static final String DISCOUNT_ID = "id";
    private static final String DISCOUNT_VALUE = "value";
    private static final String DISCOUNT_IS_AVAILABLE = "is_available";
    private static final String DISCOUNT_START_DATE = "start_date";
    private static final String DISCOUNT_FINISH_DATE = "finish_date";

    private static final int AVAILABLE_DISCOUNT = 1;

    private static final String ADD_DISCOUNT_QUERY =
            "INSERT INTO " + DATABASE + "." + DISCOUNT_TABLE + " (" +
                DISCOUNT_VALUE + ", " +
                DISCOUNT_START_DATE + ", " +
                DISCOUNT_FINISH_DATE +
            ") " +
            "VALUES (?, ?, ?)";

    private static final String UPDATE_DISCOUNT_QUERY =
            "UPDATE " + DATABASE + "." + DISCOUNT_TABLE +
            " SET " +
                DISCOUNT_VALUE + "=?, " +
                DISCOUNT_START_DATE + "=?, " +
                DISCOUNT_FINISH_DATE + "=?" +
            " WHERE " +
                DISCOUNT_ID + "=?";

    private static final String GET_DISCOUNT_QUERY = "{call getDiscount(?)}";

    private static final String GET_DISCOUNTS_FOR_DATE_QUERY =
            "SELECT " +
                DISCOUNT_ID + ", " +
                DISCOUNT_VALUE + ", " +
                DISCOUNT_START_DATE + ", " +
                DISCOUNT_FINISH_DATE +
            " FROM " +
                DATABASE + "." + DISCOUNT_TABLE +
            " WHERE " +
                DISCOUNT_START_DATE + "<=? AND " +
                DISCOUNT_FINISH_DATE + ">=? AND " +
                DISCOUNT_IS_AVAILABLE + "=" + AVAILABLE_DISCOUNT;

    private static final String GET_DISCOUNTS_QUERY = "{call getDiscounts()}";

    private static final String ERROR_ADDING = "Error during adding new entity";
    private static final String ERROR_UPDATE = "Error during updating";
    private static final String ERROR_DELETING = "Error during changing state";

    @Override
    public void addDiscount(Discount discount) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_DISCOUNT_QUERY);

            statement.setByte(1, discount.getValue());
            statement.setDate(2, (Date) discount.getStartDate());
            statement.setDate(3, (Date) discount.getFinishDate());

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_ADDING);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void updateDiscount(int discountId, Discount discount) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE_DISCOUNT_QUERY);

            statement.setByte(1, discount.getValue());
            statement.setDate(2, (Date) discount.getStartDate());
            statement.setDate(3, (Date) discount.getFinishDate());
            statement.setInt(4, discountId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_UPDATE);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public Discount getDiscount(int discountId) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_DISCOUNT_QUERY);

            statement.setInt(1, discountId);

            set = statement.executeQuery();

            Discount discount = null;

            while (set.next()) {
                discount = new Discount();

                Date startDate = set.getDate(DISCOUNT_START_DATE);
                Date finishDate = set.getDate(DISCOUNT_FINISH_DATE);
                byte value = set.getByte(DISCOUNT_VALUE);

                discount.setId(discountId);
                discount.setStartDate(startDate);
                discount.setFinishDate(finishDate);
                discount.setValue(value);

            }
            return discount;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Discount> getDiscounts() throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Discount> discounts = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_DISCOUNTS_QUERY);

            set = statement.executeQuery();

            discounts = new ArrayList<Discount>();

            while (set.next()) {
                Discount discount = new Discount();

                int discountId = set.getInt(DISCOUNT_ID);
                Date startDate = set.getDate(DISCOUNT_START_DATE);
                Date finishDate = set.getDate(DISCOUNT_FINISH_DATE);
                byte value = set.getByte(DISCOUNT_VALUE);

                discount.setId(discountId);
                discount.setValue(value);
                discount.setStartDate(startDate);
                discount.setFinishDate(finishDate);

                discounts.add(discount);
            }
            return discounts;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Discount> getDiscountForDate(Date date) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Discount> discounts = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_DISCOUNTS_FOR_DATE_QUERY);

            statement.setDate(1, ArgumentExchanger.exchangeToSQLDate(date));

            set = statement.executeQuery();

            discounts = new ArrayList<Discount>();

            while (set.next()) {
                Discount discount = new Discount();

                int categoryId = set.getInt(DISCOUNT_ID);
                java.util.Date startDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(DISCOUNT_START_DATE));
                java.util.Date finishDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(DISCOUNT_FINISH_DATE));
                byte value = set.getByte(DISCOUNT_VALUE);

                discount.setId(categoryId);
                discount.setStartDate(startDate);
                discount.setFinishDate(finishDate);
                discount.setValue(value);

                discounts.add(discount);
            }
            return discounts;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void setDiscountAvailable(int discountId, boolean available) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE_DISCOUNT_QUERY);
            statement.setBoolean(1, available);
            statement.setInt(2, discountId);

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
