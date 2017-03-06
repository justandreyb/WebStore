package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Order;
import com.training.web_store.dao.OrderDAO;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;

public class OrderDAOImpl implements OrderDAO {
    //TODO: Write
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
