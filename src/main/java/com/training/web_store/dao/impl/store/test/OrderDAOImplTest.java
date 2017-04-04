package com.training.web_store.dao.impl.store.test;

import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDAOImplTest {
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
    public void addOrder() throws Exception {

    }

    @Test
    public void getOrder() throws Exception {

    }

    @Test
    public void setOrderState() throws Exception {

    }

}