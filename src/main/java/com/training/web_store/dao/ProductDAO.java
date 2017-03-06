package com.training.web_store.dao;

import com.training.web_store.bean.store.Product;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {
    void addProduct(String name, int categoryId) throws DAOException;
    void addProduct(String name, int categoryId, int discountId) throws DAOException;

    void updateProduct(int productId, String name, int categoryId, int discountId) throws DAOException;

    Product getProduct(String name, int categoryId) throws DAOException;
    List<Product> getProducts(String requestedName) throws DAOException;
    List<Product> getProducts(int categoryId) throws DAOException;

    void setProductAvailable(int productId, boolean available) throws DAOException;
}
