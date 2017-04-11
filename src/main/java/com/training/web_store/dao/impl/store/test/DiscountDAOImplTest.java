package com.training.web_store.dao.impl.store.test;

import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

public class DiscountDAOImplTest {
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
    public void addDiscount() throws Exception {

    }

    @Test
    public void updateDiscount() throws Exception {

    }

    @Test
    public void getDiscount() throws Exception {

    }

    @Test
    public void getDiscountForDate() throws Exception {

    }

    @Test
    public void setDiscountAvailable() throws Exception {

    }

}