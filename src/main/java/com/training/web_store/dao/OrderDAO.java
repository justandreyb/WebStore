package com.training.web_store.dao;

import com.training.web_store.bean.store.Order;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;

public interface OrderDAO {
    void addOrder(Order order) throws DAOException;

    Order getOrder(int userId, int productId, Date creationDate) throws DAOException;

    void setOrderState(Order order, boolean state) throws DAOException;
}
