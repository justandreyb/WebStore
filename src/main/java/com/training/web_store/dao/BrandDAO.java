package com.training.web_store.dao;

import com.training.web_store.bean.store.Brand;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public interface BrandDAO {
    void addBrand(String name) throws DAOException, StorageException;
    void addBrand(String name, String description) throws DAOException, StorageException;

    void updateBrand(int brandId, String name, String updatedDescription) throws DAOException, StorageException;

    Brand getBrand(int brandId) throws DAOException, StorageException;
    List<Brand> getBrands() throws DAOException, StorageException;

    void setBrandAvailable(int brandId, boolean available) throws DAOException, StorageException;
}
