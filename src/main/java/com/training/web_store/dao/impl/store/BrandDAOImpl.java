package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Brand;
import com.training.web_store.dao.BrandDAO;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public class BrandDAOImpl implements BrandDAO {
    //TODO: Write
    @Override
    public void addBrand(String name) throws DAOException {

    }

    @Override
    public void addBrand(String name, String description) throws DAOException {

    }

    @Override
    public void updateBrand(int brandId, String name, String updatedDescription) throws DAOException {

    }

    @Override
    public Brand getBrand(int brandId) throws DAOException {
        return null;
    }

    @Override
    public List<Brand> getBrands() throws DAOException {
        return null;
    }

    @Override
    public void setBrandAvailable(int brandId, boolean available) throws DAOException {

    }
}
