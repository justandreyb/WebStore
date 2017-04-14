package com.training.web_store.dao;

import com.training.web_store.bean.store.Product;
import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public interface ProductDAO {
    void addProduct(String name, double price, int categoryId) throws DAOException, StorageException;
    void addProduct(String name, double price, int categoryId, int discountId) throws DAOException, StorageException;

    void updateProduct(int productId, String name, double price, int categoryId) throws DAOException, StorageException;
    void updateProduct(int productId, String name, double price, int categoryId, int discountId) throws DAOException, StorageException;

    void addThing(int productId, int thingId) throws DAOException, StorageException;

    Product getProduct(int productId) throws DAOException, StorageException;
    Product getProduct(String name, int categoryId) throws DAOException;
    List<Product> getProducts() throws DAOException, StorageException;
    List<Product> searchProduct(String requestedName) throws DAOException, StorageException;
    List<Product> getProductsForCategory(int categoryId) throws DAOException, StorageException;
    List<Product> getProductsForBrand(int brandId) throws DAOException, StorageException;
    List<Product> getProductsForDiscount(int discountId) throws DAOException, StorageException;

    void setProductAvailable(int productId, boolean available) throws DAOException, StorageException;

    void removeThing(int productId, int thingId) throws DAOException, StorageException;
}
