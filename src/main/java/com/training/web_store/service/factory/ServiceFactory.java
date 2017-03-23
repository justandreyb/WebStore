package com.training.web_store.service.factory;

import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.dao.util.DBConnector;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.impl.StoreServiceImpl;
import com.training.web_store.service.UserService;
import com.training.web_store.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();

    private final UserService userServiceImpl = new UserServiceImpl();
    private final StoreService storeServiceImpl = new StoreServiceImpl();

    private ServiceFactory() {
    }

    public void init() throws ServiceException {
        DBConnector connector = DBConnector.getInstance();
        try {
            connector.init();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static ServiceFactory getInstance() {
        return factory;
    }

    public UserService getUserService() {
        return userServiceImpl;
    }

    public StoreService getStoreService() {
        return storeServiceImpl;
    }
}
