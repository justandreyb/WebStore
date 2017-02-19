package com.training.web_store.service.factory;

import com.training.web_store.service.UserService;
import com.training.web_store.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();

    private final UserService userServiceImpl = new UserServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return factory;
    }

    public UserService getUserService() {
        return userServiceImpl;
    }
}
