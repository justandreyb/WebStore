package com.training.web_store.dao;

import com.training.web_store.bean.store.Brand;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public interface BrandDAO {
    void addBrand(String name) throws DAOException;
    void addBrand(String name, String description) throws DAOException;

    void updateBrand(int brandId, String name, String updatedDescription) throws DAOException;

    Brand getBrand(int brandId) throws DAOException;
    List<Brand> getBrands() throws DAOException;

    void setBrandAvailable(int brandId, boolean available) throws DAOException;
}
