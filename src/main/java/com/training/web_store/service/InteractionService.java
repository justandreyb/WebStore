package com.training.web_store.service;

import com.training.web_store.bean.store.Order;
import com.training.web_store.bean.store.Product;
import com.training.web_store.service.exception.ServiceException;

import java.util.List;

public interface InteractionService {
    Order createOrder(int userId) throws ServiceException;
    Order getOrder(int userId) throws ServiceException;
    void addToOrder(int orderId, int productId) throws ServiceException;
    void removeFromOrder(int orderId, int productId) throws ServiceException;
    void buyOrder(int orderId) throws ServiceException;

    List<Product> searchProduct(String requestedProduct) throws ServiceException;
    void setRating(int userId, int thingId, byte value) throws ServiceException;
}
