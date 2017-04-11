package com.training.web_store.dao.impl.store.test;

import com.training.web_store.bean.store.Category;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CategoryDAOImplTest {
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
    public void addCategory() throws Exception {

    }

    @Test
    public void addCategory1() throws Exception {

    }

    @Test
    public void updateCategory() throws Exception {

    }

    @Test
    public void getCategory() throws Exception {

    }

    @Test
    public void getCategories() throws Exception {
        List<Category> categories = service.getCategories();
        for (Category category : categories) {
            System.out.println(category.getName());
        }
    }

    @Test
    public void setAvailable() throws Exception {

    }

}