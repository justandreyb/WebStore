package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;
import com.training.util.database.DBConnector;

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

    private static final String GET_CATEGORY_QUERY = "{call getCategory(?)}";
    private static final String GET_CATEGORIES_QUERY = "{call getCategories()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        return null;
    }
}
