package com.training.web_store.dao.impl.store.test;

import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductDAOImplTest {
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
    public void addProduct() throws Exception {

    }

    @Test
    public void addProduct1() throws Exception {

    }

    @Test
    public void updateProduct() throws Exception {

    }

    @Test
    public void addThing() throws Exception {

    }

    @Test
    public void getProduct() throws Exception {

    }

    @Test
    public void getProducts() throws Exception {

    }

    @Test
    public void getProducts1() throws Exception {

    }

    @Test
    public void getProductsForDiscount() throws Exception {

    }

    @Test
    public void getThings() throws Exception {

    }

    @Test
    public void setProductAvailable() throws Exception {

    }

    @Test
    public void removeThing() throws Exception {

    }

}