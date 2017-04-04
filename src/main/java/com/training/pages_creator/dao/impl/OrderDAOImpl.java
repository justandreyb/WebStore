package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;

import java.util.List;

public class OrderDAOImpl implements EntityDAO {
    private static final String DATABASE = "web_store";
    private static final String ORDER_TABLE = "order";
    private static final String ORDER_ID = "id";
    private static final String ORDER_PRICE = "price";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String ORDER_IS_COMPLETE = "is_complete";
    private static final String ORDER_CREATION_DATE = "creation_date";

    private static final String GET_ORDER_QUERY =
            "SELECT " +
                ORDER_ID + ", " +
                ORDER_PRICE + ", " +
                ORDER_CREATION_DATE + ", " +
                ORDER_IS_COMPLETE +
            " FROM " +
                DATABASE + "." + ORDER_TABLE +
            " WHERE " +
                CUSTOMER_ID + "=?";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        return null;
    }
}
