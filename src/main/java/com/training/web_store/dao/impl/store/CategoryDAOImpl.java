package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Category;
import com.training.web_store.dao.CategoryDAO;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    //TODO: Write
    @Override
    public void addCategory(String name) throws DAOException {

    }

    @Override
    public void addCategory(String name, String description) throws DAOException {

    }

    @Override
    public void updateCategory(int categoryId, String name, String updatedDescription) throws DAOException {

    }

    @Override
    public Category getCategory(int categoryId) throws DAOException {
        return null;
    }

    @Override
    public List<Category> getCategories() throws DAOException {
        return null;
    }

    @Override
    public void setAvailable(int categoryId, boolean available) throws DAOException {

    }
}
