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
public class BrandDAOImpl implements EntityDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String BRAND_ID = "id";
    private static final String BRAND_NAME = "name";
    private static final String BRAND_DESCRIPTION = "description";

    private static final String BRAND_PREFIX = "brand_";

    private static final String GET_BRAND_QUERY = "{call getBrand(?)}";
    private static final String GET_BRANDS_QUERY = "{call getBrands()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        CollectedData data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_BRAND_QUERY);

            statement.setInt(1, objectId);

            set = statement.executeQuery();

            data = new CollectedData();

            while (set.next()) {
                int brandId = set.getInt(BRAND_ID);
                String name = set.getString(BRAND_NAME);
                String description = set.getString(BRAND_DESCRIPTION);

                data.addObject(BRAND_PREFIX + BRAND_ID, String.valueOf(brandId));
                data.addObject(BRAND_PREFIX + BRAND_NAME, name);
                data.addObject(BRAND_PREFIX + BRAND_DESCRIPTION, description);
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
    public List<CollectedData> getObjects() throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<CollectedData> data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_BRANDS_QUERY);
            set = statement.executeQuery();

            data = new ArrayList<CollectedData>();

            while (set.next()) {
                int brandId = set.getInt(BRAND_ID);
                String name = set.getString(BRAND_NAME);

                CollectedData collectedData = new CollectedData();

                collectedData.addObject(BRAND_PREFIX + BRAND_ID, String.valueOf(brandId));
                collectedData.addObject(BRAND_PREFIX + BRAND_NAME, name);

                data.add(collectedData);
            }
            return data;

        } catch (SQLException/* | ProjectUtilException*/ e) {
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
