package com.training.web_store.service.impl.search.impl;

import com.training.web_store.bean.store.Photo;
import com.training.web_store.bean.store.Product;
import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.service.SearchService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.impl.search.SearchServiceImpl;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public class SearchByBrandService extends SearchServiceImpl {

    @Override
    public List<Product> getProducts(String request) throws ServiceException {
        try {
            List<Product> products = productDAO.searchProductByBrand(request);
            for (Product product : products) {
                List<Photo> photos = photoDAO.getPhotosForProduct(product.getId());
                product.setPhotos(photos);
            }
            return products;
        } catch (DAOException | StorageException e) {
            throw new ServiceException("Error while search products by brand", e);
        }
    }
}
