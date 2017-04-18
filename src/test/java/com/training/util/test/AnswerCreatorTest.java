package com.training.util.test;

import com.training.util.AnswerCreator;
import com.training.web_store.bean.store.*;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AnswerCreatorTest {
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

    @After
    public void tearDown() throws Exception {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        try {
            serviceFactory.close();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void create() throws Exception {
        System.out.println("create()");
        System.out.println(AnswerCreator.create("TEST"));
    }

    @Test
    public void createError() throws Exception {
        System.out.println("createError()");
        System.out.println(AnswerCreator.createError("TEST_ERROR"));
    }

    @Test
    public void createJSONFromBrand() throws Exception {
        System.out.println("createBrand()");
        Brand brand = service.getBrand(1);

        System.out.println(AnswerCreator.createJSONFromBrand(brand));
    }

    @Test
    public void createJSONFromBrands() throws Exception {
        System.out.println("createBrands()");
        List<Brand> brands = service.getBrands();

        System.out.println(AnswerCreator.createJSONFromBrands(brands));
    }

    @Test
    public void createJSONFromCategory() throws Exception {
        System.out.println("createCategory()");
        Category category = service.getCategory(1);

        System.out.println(AnswerCreator.createJSONFromCategory(category));
    }

    @Test
    public void createJSONFromCategories() throws Exception {
        System.out.println("createCategories()");
        List<Category> categories = service.getCategories();

        System.out.println(AnswerCreator.createJSONFromCategories(categories));
    }

    @Test
    public void createJSONFromProduct() throws Exception {
        System.out.println("createProduct()");
        Product product = service.getProduct(1);

        System.out.println(AnswerCreator.createJSONFromProduct(product));
    }

    @Test
    public void createJSONFromProducts() throws Exception {
        System.out.println("createProducts()");
        List<Product> products = service.getProducts();

        System.out.println(AnswerCreator.createJSONFromProducts(products));
    }

    @Test
    public void createJSONFromPhotos() throws Exception {
        System.out.println("createPhotos()");
        List<Photo> photos = service.getPhotos(2);

        System.out.println(AnswerCreator.createJSONFromPhotos(photos));
    }

    @Test
    public void createJSONFromDiscount() throws Exception {
        System.out.println("createDiscount()");
        Discount discount = service.getDiscount(1);

        System.out.println(AnswerCreator.createJSONFromDiscount(discount));
    }

    @Test
    public void createJSONFromDiscounts() throws Exception {
        System.out.println("createDiscounts()");
        List<Discount> discounts = service.getDiscounts();

        System.out.println(AnswerCreator.createJSONFromDiscounts(discounts));
    }

    @Test
    public void createJSONFromThing() throws Exception {
        System.out.println("createThing()");
        Thing thing = service.getThing(1);

        System.out.println(AnswerCreator.createJSONFromThing(thing));
    }

    @Test
    public void createJSONFromThings() throws Exception {
        System.out.println("createThings()");
        List<Thing> things = service.getThings();

        System.out.println(AnswerCreator.createJSONFromThings(things));
    }

    @Test
    public void createJSONFromReview() throws Exception {
        System.out.println("createReview()");
        System.out.println(AnswerCreator.createJSONFromReview(service.getReview(1)));
    }

}