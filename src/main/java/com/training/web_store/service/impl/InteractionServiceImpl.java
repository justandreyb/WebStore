package com.training.web_store.service.impl;

import com.training.web_store.bean.store.Order;
import com.training.web_store.bean.store.Product;
import com.training.web_store.service.InteractionService;
import com.training.web_store.service.exception.ServiceException;

import java.util.List;

public class InteractionServiceImpl implements InteractionService {

    @Override
    public Order createOrder(int userId) throws ServiceException {
        return null;
    }

    @Override
    public Order getOrder(int userId) throws ServiceException {
        return null;
    }

    @Override
    public void addToOrder(int orderId, int productId) throws ServiceException {

    }

    @Override
    public void removeFromOrder(int orderId, int productId) throws ServiceException {

    }

    @Override
    public void buyOrder(int orderId) throws ServiceException {

    }

    @Override
    public List<Product> searchProduct(String requestedProduct) throws ServiceException {
        return null;
    }

    @Override
    public void setRating(int userId, int thingId, byte value) throws ServiceException {

    }
}
