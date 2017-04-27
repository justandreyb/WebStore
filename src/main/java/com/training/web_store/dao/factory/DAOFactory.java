package com.training.web_store.dao.factory;

import com.training.web_store.dao.*;
import com.training.web_store.dao.impl.account.RoleDAOImpl;
import com.training.web_store.dao.impl.account.UserDAOImpl;
import com.training.web_store.dao.impl.store.*;

public class DAOFactory {
    private static final DAOFactory factory = new DAOFactory();

    private final UserDAO userDAOImpl = new UserDAOImpl();
    private final ProductDAO productDAOImpl = new ProductDAOImpl();
    private final ThingDAO thingDAOImpl = new ThingDAOImpl();
    private final BrandDAO brandDAOImpl = new BrandDAOImpl();
    private final CategoryDAO categoryDAOImpl = new CategoryDAOImpl();
    private final DiscountDAO discountDAOImpl = new DiscountDAOImpl();
    private final OrderDAO orderDAOImpl = new OrderDAOImpl();
    private final PhotoDAO photoDAOImpl = new PhotoDAOImpl();
    private final RoleDAO roleDAOImpl = new RoleDAOImpl();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return factory;
    }

    public UserDAO getUserDAO() {
        return userDAOImpl;
    }

    public ProductDAO getProductDAO() {
        return productDAOImpl;
    }

    public ThingDAO getThingDAO() {
        return thingDAOImpl;
    }

    public BrandDAO getBrandDAO() {
        return brandDAOImpl;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAOImpl;
    }

    public DiscountDAO getDiscountDAO() {
        return discountDAOImpl;
    }

    public OrderDAO getOrderDAO() {
        return orderDAOImpl;
    }

    public PhotoDAO getPhotoDAO() {
        return photoDAOImpl;
    }

    public RoleDAO getRoleDAO() {
        return roleDAOImpl;
    }
}
