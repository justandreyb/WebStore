package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;
import com.training.util.database.DBConnector;

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

    private static final String GET_DISCOUNT_QUERY = "{call getDiscount(?)}";
    private static final String GET_DISCOUNTS_QUERY = "{call getDiscounts()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        return null;
    }
}
