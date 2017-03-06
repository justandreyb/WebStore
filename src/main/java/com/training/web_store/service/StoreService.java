package com.training.web_store.service;

import com.training.web_store.bean.store.*;
import com.training.web_store.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface StoreService {
    void addBrand(String name) throws ServiceException;
    void addBrand(String name, String description) throws ServiceException;

    Brand getBrand(int brandId) throws ServiceException;
    List<Brand> getBrands() throws ServiceException;

    void updateBrand(int brandId, String name, String description) throws ServiceException;

    void deleteBrand(int brandId) throws ServiceException;

    /* --------------------------------------------------------------------- */

    void addCategory(String name) throws ServiceException;
    void addCategory(String name, String description) throws ServiceException;

    Category getCategory(int categoryId) throws ServiceException;

    void updateCategory(int categoryId, String name, String description) throws ServiceException;

    void deleteCategory(int categoryId) throws ServiceException;

    /* --------------------------------------------------------------------- */

    void addDiscount(byte value, Date startDate, Date finishDate) throws ServiceException;

    Discount getDiscount(int discountId) throws ServiceException;
    List<Discount> getDiscounts(Date date) throws ServiceException;

    void deleteDiscount(int discountId) throws ServiceException;

    /* --------------------------------------------------------------------- */

    void addPhoto(int thingId, String name) throws ServiceException;

    List<Photo> getPhotos(int thingId) throws ServiceException;

    void deletePhoto(int photoId) throws ServiceException;

    /* --------------------------------------------------------------------- */

    void addProduct(String name, int categoryId) throws ServiceException;
    void addProduct(String name, int categoryId, int discountId) throws ServiceException;

    void addThing(int productId, int thingId) throws ServiceException;
    void removeThing(int productId, int thingId) throws ServiceException;

    Product getProduct(String name, int categoryId) throws ServiceException;
    List<Product> getProducts(String requestedProduct) throws ServiceException;
    List<Product> getProductsForCategory(int categoryId) throws ServiceException;
    List<Product> getProductsForBrand(int brandId) throws ServiceException;

    void updateProduct(int productId, String name, int categoryId, int discountId) throws ServiceException;

    void deleteProduct(int productId) throws ServiceException;

    /* --------------------------------------------------------------------- */

    void addThing(String name, String description, Date creationDate) throws ServiceException;

    void addReview(int thingId, String review) throws ServiceException;
    void addRating(int userId, int thingId, byte value) throws ServiceException;

    Thing getThing(int thingId) throws ServiceException;
    byte getRating(int thingId) throws ServiceException;

    void updateThing(int thingId, String name, String description, Date creationDate) throws ServiceException;

    void deleteThing(int thingId) throws ServiceException;
}
