package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;
import com.training.util.database.DBConnector;

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

    private static final String GET_PHOTOS_QUERY = "";

    @Override
    public CollectedData getObject(int photoId) throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        return null;
    }
}
