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
import java.util.Date;
import java.util.List;

@SuppressWarnings("Duplicates")
public class DiscountDAOImpl implements EntityDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String DISCOUNT_TABLE = "discount";
    private static final String DISCOUNT_ID = "id";
    private static final String DISCOUNT_VALUE = "value";
    private static final String DISCOUNT_IS_AVAILABLE = "is_available";
    private static final String DISCOUNT_START_DATE = "start_date";
    private static final String DISCOUNT_FINISH_DATE = "finish_date";
    private static final String DISCOUNT_PREFIX = "discount_";

    private static final String GET_DISCOUNT_QUERY = "{call getDiscount(?)}";
    private static final String GET_DISCOUNTS_QUERY = "{call getDiscounts()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        CollectedData data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_DISCOUNT_QUERY);

            statement.setInt(1, objectId);

            set = statement.executeQuery();

            data = new CollectedData();

            while (set.next()) {
                int discountId = set.getInt(DISCOUNT_ID);
                Byte value = set.getByte(DISCOUNT_VALUE);
                Date startDate = set.getDate(DISCOUNT_START_DATE);
                Date finishDate = set.getDate(DISCOUNT_FINISH_DATE);

                data.addObject(DISCOUNT_PREFIX + DISCOUNT_ID, String.valueOf(discountId));
                data.addObject(DISCOUNT_PREFIX + DISCOUNT_VALUE, String.valueOf(value));
                data.addObject(DISCOUNT_PREFIX + DISCOUNT_START_DATE, String.valueOf(startDate));
                data.addObject(DISCOUNT_PREFIX + DISCOUNT_FINISH_DATE, String.valueOf(finishDate));
            }
            return data;

        } catch (SQLException /*| ProjectUtilException*/ e) {
            throw new DAOException(e);
        } finally {
//            try {
                dbConnector.closeConnection(connection, statement, set);
           /* } catch (ProjectUtilException e) {
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
            statement = connection.prepareCall(GET_DISCOUNTS_QUERY);

            set = statement.executeQuery();

            data = new ArrayList<CollectedData>();


            while (set.next()) {
                int discountId = set.getInt(DISCOUNT_ID);
                Byte value = set.getByte(DISCOUNT_VALUE);
                Date startDate = set.getDate(DISCOUNT_START_DATE);
                Date finishDate = set.getDate(DISCOUNT_FINISH_DATE);

                CollectedData collectedData = new CollectedData();

                collectedData.addObject(DISCOUNT_PREFIX + DISCOUNT_ID, String.valueOf(discountId));
                collectedData.addObject(DISCOUNT_PREFIX + DISCOUNT_VALUE, String.valueOf(value));
                collectedData.addObject(DISCOUNT_PREFIX + DISCOUNT_START_DATE, String.valueOf(startDate));
                collectedData.addObject(DISCOUNT_PREFIX + DISCOUNT_FINISH_DATE, String.valueOf(finishDate));

                data.add(collectedData);
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
    public List<CollectedData> getObjects(int objectId) throws DAOException {
        return null;
    }
}
