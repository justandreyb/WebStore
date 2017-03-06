package com.training.web_store.service.factory;

import com.training.web_store.service.impl.store.*;
import com.training.web_store.service.store.*;
import com.training.web_store.service.UserService;
import com.training.web_store.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();

    private final UserService userServiceImpl = new UserServiceImpl();
    private final BrandService brandServiceImpl = new BrandServiceImpl();
    private final CategoryService categoryServiceImpl = new CategoryServiceImpl();
    private final DiscountService discountServiceImpl = new DiscountServiceImpl();
    private final PhotoService photoServiceImpl = new PhotoServiceImpl();
    private final ProductService productServiceImpl = new ProductServiceImpl();
    private final ThingService thingServiceImpl = new ThingServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return factory;
    }

    public UserService getUserService() {
        return userServiceImpl;
    }

    public BrandService getBrandService() {
        return brandServiceImpl;
    }

    public CategoryService getCategoryService() {
        return categoryServiceImpl;
    }

    public DiscountService getDiscountService() {
        return discountServiceImpl;
    }

    public PhotoService getPhotoService() {
        return photoServiceImpl;
    }

    public ProductService getProductService() {
        return productServiceImpl;
    }

    public ThingService getThingService() {
        return thingServiceImpl;
    }
}
