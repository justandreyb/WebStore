package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Category;
import com.training.web_store.dao.CategoryDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.database.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class CategoryDAOImpl implements CategoryDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String CATEGORY_TABLE = "category";
    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_DESCRIPTION = "description";
    private static final String CATEGORY_IS_AVAILABLE = "is_available";

    private static final String EMPTY_DESCRIPTION = null;

    private static final String ADD_CATEGORY_QUERY =
            "INSERT INTO " + DATABASE + "." + CATEGORY_TABLE + " (" +
                CATEGORY_NAME + ", " +
                CATEGORY_DESCRIPTION +
            ") " +
            "VALUES (?, ?)";

    private static final String UPDATE_CATEGORY_QUERY =
            "UPDATE " + DATABASE + "." + CATEGORY_TABLE +
            " SET " +
                CATEGORY_NAME + "=?, " +
                CATEGORY_DESCRIPTION + "=?" +
            " WHERE " +
                CATEGORY_ID + "=?";

    private static final String GET_CATEGORY_QUERY = "{call getCategory(?)}";

    private static final String GET_CATEGORIES_QUERY = "{call getCategories()}";

    private static final String SET_CATEGORY_AVAILABLE =
            "UPDATE " + DATABASE + "." + CATEGORY_TABLE +
            " SET " +
                CATEGORY_IS_AVAILABLE + "=?" +
            " WHERE " +
                CATEGORY_ID + "=?";

    private static final String ERROR_ADDING = "Error during adding new entity";
    private static final String ERROR_UPDATE = "Error during updating";
    private static final String ERROR_DELETING = "Error during changing state";

    @Override
    public void addCategory(String name) throws DAOException {
        addCategory(name, EMPTY_DESCRIPTION);
    }

    @Override
    public void addCategory(String name, String description) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_CATEGORY_QUERY);

            statement.setString(1, name);
            //TODO: Mb just send null without check?
            if (description != null) {
                statement.setString(2, description);
            }

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
    public void updateCategory(int categoryId, String name, String updatedDescription) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE_CATEGORY_QUERY);

            statement.setString(1, name);
            statement.setString(2, updatedDescription);
            statement.setInt(3, categoryId);

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
    public Category getCategory(int categoryId) throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_CATEGORY_QUERY);

            statement.setInt(1, categoryId);

            set = statement.executeQuery();

            Category category = null;

            while (set.next()) {
                category = new Category();

                category.setId(categoryId);
                String name = set.getString(CATEGORY_NAME);
                String description = set.getString(CATEGORY_DESCRIPTION);

                category.setName(name);
                category.setDescription(description);

            }
            return category;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Category> getCategories() throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Category> categories = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_CATEGORIES_QUERY);

            set = statement.executeQuery();

            categories = new ArrayList<Category>();

            while (set.next()) {
                Category category = new Category();

                int categoryId = set.getInt(CATEGORY_ID);
                String name = set.getString(CATEGORY_NAME);
                String description = set.getString(CATEGORY_DESCRIPTION);

                category.setId(categoryId);
                category.setName(name);
                category.setDescription(description);

                categories.add(category);
            }
            return categories;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void setAvailable(int categoryId, boolean available) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_CATEGORY_AVAILABLE);
            statement.setBoolean(1, available);
            statement.setInt(2, categoryId);

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
