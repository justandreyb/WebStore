package com.training.web_store.dao.impl.store.test;

import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

public class PhotoDAOImplTest {
    StoreService service;

    @Before
    public void setUp() throws Exception {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        try {
            serviceFactory.init();
            service = serviceFactory.getStoreService();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addThingPhoto() throws Exception {

    }

    @Test
    public void addProductPhoto() throws Exception {

    }

    @Test
    public void getPhotos() throws Exception {

    }

    @Test
    public void setAvailable() throws Exception {

    }

}