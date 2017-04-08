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
public class PhotoDAOImpl implements EntityDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String PHOTO_TABLE = "thing_image";
    private static final String PHOTO_ID = "id";
    private static final String PHOTO_HREF = "href";
    private static final String PHOTO_REAL_NAME = "real_name";
    private static final String THING_ID = "thing_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String PHOTO_IS_AVAILABLE = "is_available";
    private static final String PHOTO_PREFIX = "photo_";

    private static final String GET_PHOTOS_QUERY = "{call getPhotos()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<CollectedData> data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_PHOTOS_QUERY);

            set = statement.executeQuery();

            data = new ArrayList<CollectedData>();


            while (set.next()) {
                int discountId = set.getInt(PHOTO_ID);
                String href = set.getString(PHOTO_HREF);

                CollectedData collectedData = new CollectedData();

                collectedData.addObject(PHOTO_PREFIX + PHOTO_ID, String.valueOf(discountId));
                collectedData.addObject(PHOTO_PREFIX + PHOTO_HREF, href);

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
