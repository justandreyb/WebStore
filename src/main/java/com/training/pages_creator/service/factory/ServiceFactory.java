package com.training.pages_creator.service.factory;

import com.training.pages_creator.service.ViewService;
import com.training.pages_creator.service.impl.ViewServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() { }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ViewService viewService = new ViewServiceImpl();

    public ViewService getViewService() {
        return this.viewService;
    }
}
