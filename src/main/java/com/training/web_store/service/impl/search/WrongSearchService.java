package com.training.web_store.service.impl.search;

import com.training.web_store.bean.store.Product;
import com.training.web_store.service.SearchService;
import com.training.web_store.service.exception.ServiceException;

import java.util.List;

public class WrongSearchService implements SearchService {

    @Override
    public List<Product> getProducts(String request) throws ServiceException {
        return null;
    }
}
