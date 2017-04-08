package com.training.web_store.dao;

import com.training.util.exception.ProjectUtilException;
import com.training.web_store.bean.store.Category;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public interface CategoryDAO {
    void addCategory(String name) throws DAOException;
    void addCategory(String name, String description) throws DAOException;

    void updateCategory(int categoryId, String name, String updatedDescription) throws DAOException;

    Category getCategory(int categoryId) throws DAOException;
    List<Category> getCategories() throws DAOException;

    void setAvailable(int categoryId, boolean available) throws DAOException;
}
