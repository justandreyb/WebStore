package com.training.web_store.dao;

import com.training.web_store.bean.store.Order;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order, int userId) throws DAOException, StorageException;

    Order getOrder(int userId, int orderId) throws DAOException, StorageException;
    List<Order> getOrders(int userId) throws DAOException, StorageException;

    void setOrderState(int orderId, boolean state) throws DAOException, StorageException;

    void addToOrder(int orderId, int productId) throws DAOException, StorageException;
    void deleteFromOrder(int orderId, int productId) throws DAOException, StorageException;
}
