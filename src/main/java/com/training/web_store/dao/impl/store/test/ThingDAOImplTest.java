package com.training.web_store.dao.impl.store.test;

import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

public class ThingDAOImplTest {
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
    public void addThing() throws Exception {

    }

    @Test
    public void addThingReview() throws Exception {

    }

    @Test
    public void addRating() throws Exception {

    }

    @Test
    public void updateThing() throws Exception {

    }

    @Test
    public void getThing() throws Exception {

    }

    @Test
    public void getRating() throws Exception {

    }

    @Test
    public void setThingAvailable() throws Exception {

    }

}