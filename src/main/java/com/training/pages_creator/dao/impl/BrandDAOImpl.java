package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;
import com.training.util.database.DBConnector;

import java.util.List;

@SuppressWarnings("Duplicates")
public class BrandDAOImpl implements EntityDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String BRAND_TABLE = "brand";
    private static final String BRAND_ID = "id";
    private static final String BRAND_NAME = "name";
    private static final String BRAND_IS_AVAILABLE = "is_available";
    private static final String BRAND_DESCRIPTION = "description";

    private static final String GET_BRAND_QUERY = "{call getBrand(?)}";
    private static final String GET_BRANDS_QUERY = "{call getBrands()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        return null;
    }
}
