package com.training.web_store.dao;

import com.training.web_store.bean.store.Order;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order) throws DAOException;

    Order getOrder(int userId, int orderId) throws DAOException;
    List<Order> getOrders(int userId) throws DAOException;

    void setOrderState(int orderId, boolean state) throws DAOException;
}
