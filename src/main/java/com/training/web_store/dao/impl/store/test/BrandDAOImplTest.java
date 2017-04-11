package com.training.web_store.dao.impl.store.test;

import com.training.web_store.bean.store.Brand;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BrandDAOImplTest {
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
    public void addBrand() throws Exception {

    }

    @Test
    public void addBrandWithName() throws Exception {

    }

    @Test
    public void updateBrand() throws Exception {

    }

    @Test
    public void getBrand() throws Exception {

    }

    @Test
    public void getBrands() throws Exception {
        List<Brand> brands = service.getBrands();
        for (Brand brand : brands) {
            System.out.println(brand.getName());
        }
    }

    @Test
    public void setBrandAvailable() throws Exception {

    }

}