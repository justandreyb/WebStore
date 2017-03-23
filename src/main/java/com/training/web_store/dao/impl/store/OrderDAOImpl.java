package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Order;
import com.training.web_store.dao.OrderDAO;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;

public class OrderDAOImpl implements OrderDAO {
    private static final String DATABASE = "web_store";
    private static final String ORDER_TABLE = "order";
    private static final String ORDER_ID = "id";
    private static final String ORDER_PRICE = "price";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String ORDER_IS_COMPLETE = "is_complete";
    private static final String ORDER_CREATION_DATE = "creation_date";

    private static final int IS_COMPLETED = 1;

    private static final String ADD_ORDER_QUERY =
            "INSERT INTO " + DATABASE + "." + ORDER_TABLE + " (" +
                ORDER_PRICE + ", " +
                ORDER_CREATION_DATE + ", " +
                ORDER_IS_COMPLETE +
            ") " +
            "VALUES (?, ?, ?)";

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

    private static final String SET_ORDER_STATE =
            "UPDATE " + DATABASE + "." + ORDER_TABLE +
            " SET " +
                ORDER_IS_COMPLETE + "=?" +
            " WHERE " +
                ORDER_ID + "=?";

    @Override
    public void addOrder(Order order) throws DAOException {

    }

    @Override
    public Order getOrder(int userId, int productId, Date creationDate) throws DAOException {
        return null;
    }

    @Override
    public void setOrderState(Order order, boolean state) throws DAOException {

    }
}
