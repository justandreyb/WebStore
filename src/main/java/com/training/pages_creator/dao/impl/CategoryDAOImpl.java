package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;
import com.training.util.database.DBConnector;
import com.training.util.exception.ProjectUtilException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class CategoryDAOImpl implements EntityDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String CATEGORY_TABLE = "category";
    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "name";
    private static final String CATEGORY_DESCRIPTION = "description";
    private static final String CATEGORY_IS_AVAILABLE = "is_available";
    private static final String CATEGORY_PREFIX = "category_";

    private static final String GET_CATEGORY_QUERY = "{call getCategory(?)}";
    private static final String GET_CATEGORIES_QUERY = "{call getCategories()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        CollectedData data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_CATEGORY_QUERY);
            set = statement.executeQuery();

            data = new CollectedData();

            while (set.next()) {
                int brandId = set.getInt(CATEGORY_ID);
                String name = set.getString(CATEGORY_NAME);
                String description = set.getString(CATEGORY_DESCRIPTION);

                data.addObject(CATEGORY_PREFIX + CATEGORY_ID, String.valueOf(brandId));
                data.addObject(CATEGORY_PREFIX + CATEGORY_NAME, name);
                data.addObject(CATEGORY_PREFIX + CATEGORY_DESCRIPTION, description);
            }
            return data;

        } catch (SQLException /*| ProjectUtilException*/ e) {
            throw new DAOException(e);
        } finally {
//            try {
                dbConnector.closeConnection(connection, statement, set);
            /*} catch (ProjectUtilException e) {
                throw new DAOException(e);
            }*/
        }
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<CollectedData> data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_CATEGORY_QUERY);
            set = statement.executeQuery();

            data = new ArrayList<CollectedData>();

            while (set.next()) {
                int brandId = set.getInt(CATEGORY_ID);
                String name = set.getString(CATEGORY_NAME);

                CollectedData collectedData = new CollectedData();

                collectedData.addObject(CATEGORY_PREFIX + CATEGORY_ID, String.valueOf(brandId));
                collectedData.addObject(CATEGORY_PREFIX + CATEGORY_NAME, name);

                data.add(collectedData);
            }
            return data;

        } catch (SQLException /*| ProjectUtilException */e) {
            throw new DAOException(e);
        } finally {
//            try {
                dbConnector.closeConnection(connection, statement, set);
            /*} catch (ProjectUtilException e) {
                throw new DAOException(e);
            }*/
        }
    }

    @Override
    public List<CollectedData> getObjects(int objectId) throws DAOException {
        return null;
    }
}
