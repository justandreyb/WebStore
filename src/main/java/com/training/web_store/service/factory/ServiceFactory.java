package com.training.web_store.service.factory;

import com.training.web_store.service.InteractionService;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.UserService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.impl.InteractionServiceImpl;
import com.training.web_store.service.impl.StoreServiceImpl;
import com.training.web_store.service.impl.UserServiceImpl;
import com.training.web_store.util.database.DBConnector;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();

    private final UserService userServiceImpl = new UserServiceImpl();
    private final StoreService storeServiceImpl = new StoreServiceImpl();
    private final InteractionService interactionService = new InteractionServiceImpl();

    private ServiceFactory() {
    }

    public void init() throws ServiceException {
        DBConnector connector = DBConnector.getInstance();
//        try {
            connector.init();
       /* } catch (ProjectUtilException e) {
            throw new ServiceException(e);
        }*/
    }

    public void close() throws ServiceException {
        DBConnector connector = DBConnector.getInstance();
//        try {
            connector.destroy();
        /*} catch (ProjectUtilException e) {
            throw new ServiceException(e);
        }*/
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

    public InteractionService getInteractionService() {
        return interactionService;
    }
}
