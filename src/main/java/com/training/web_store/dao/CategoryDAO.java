package com.training.web_store.dao;

import com.training.web_store.bean.store.Category;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public interface CategoryDAO {
    void addCategory(String name) throws DAOException, StorageException;
    void addCategory(String name, String description) throws DAOException, StorageException;

    void updateCategory(int categoryId, String name, String updatedDescription) throws DAOException, StorageException;

    Category getCategory(int categoryId) throws DAOException, StorageException;
    List<Category> getCategories() throws DAOException, StorageException;

    void setAvailable(int categoryId, boolean available) throws DAOException, StorageException;
}
