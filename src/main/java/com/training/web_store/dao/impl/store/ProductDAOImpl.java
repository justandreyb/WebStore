package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Product;
import com.training.web_store.dao.ProductDAO;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    //TODO: Write
    @Override
    public void addProduct(String name, int categoryId) throws DAOException {

    }

    @Override
    public void addProduct(String name, int categoryId, int discountId) throws DAOException {

    }

    @Override
    public void updateProduct(int productId, Product product) throws DAOException {

    }

    @Override
    public Product getProduct(String name, int categoryId) throws DAOException {
        return null;
    }

    @Override
    public List<Product> getProducts(String requestedName) throws DAOException {
        return null;
    }

    @Override
    public List<Product> getProducts(int categoryId) throws DAOException {
        return null;
    }

    @Override
    public void setProductAvailable(int productId, boolean available) throws DAOException {

    }
}
