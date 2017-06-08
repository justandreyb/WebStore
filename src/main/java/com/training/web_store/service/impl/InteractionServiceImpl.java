package com.training.web_store.service.impl;

import com.training.web_store.bean.store.Order;
import com.training.web_store.bean.store.Photo;
import com.training.web_store.bean.store.Product;
import com.training.web_store.dao.OrderDAO;
import com.training.web_store.dao.PhotoDAO;
import com.training.web_store.dao.ProductDAO;
import com.training.web_store.dao.ThingDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.dao.factory.DAOFactory;
import com.training.web_store.service.InteractionService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.util.exception.StorageException;

import java.util.Date;
import java.util.List;

public class InteractionServiceImpl implements InteractionService {

    private final DAOFactory factory = DAOFactory.getInstance();
    private final OrderDAO orderDAO = factory.getOrderDAO();
    private final ProductDAO productDAO = factory.getProductDAO();
    private final PhotoDAO photoDAO = factory.getPhotoDAO();

    private static final String INVALID_ARGUMENT = "Invalid argument";

    @Override
    public Order createOrder(int userId) throws ServiceException {
        Order order = new Order();
        order.setCustomerId(userId);
        order.setCreationDate(new Date());
        order.setComplete(false);

        try {
            orderDAO.addOrder(order, userId);
            return getMainOrder(userId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Error while creating new order" + e);
        }
    }

    @Override
    public Order getOrder(int userId) throws ServiceException {
        try {
           return getMainOrder(userId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Error while getting order" + e);
        }
    }

    private Order getMainOrder(int userId) throws StorageException, DAOException {
        Order targetOrder = null;
        List<Order> orders = null;

        orders = orderDAO.getOrders(userId);

        if (orders.size() > 0) {
            targetOrder = orders.get(0);
            List<Product> products = productDAO.getProductsForOrder(targetOrder.getId());
            for (Product product : products) {
                List<Photo> photos = photoDAO.getPhotosForProduct(product.getId());
                product.setPhotos(photos);
            }
            targetOrder.setProducts(products);

        }
        return targetOrder;
    }

    @Override
    public void addToOrder(int orderId, int productId) throws ServiceException {
        try {
            orderDAO.addToOrder(orderId, productId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Error while adding product to order" + e);
        }
    }

    @Override
    public void removeFromOrder(int orderId, int productId) throws ServiceException {
        try {
            orderDAO.deleteFromOrder(orderId, productId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Error while adding product to order" + e);
        }
    }

    @Override
    public void buyOrder(int orderId) throws ServiceException {

    }

    @Override
    public void setRating(int userId, int thingId, byte value) throws ServiceException {

    }
}
