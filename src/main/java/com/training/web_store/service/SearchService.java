package com.training.web_store.service;

import com.training.web_store.bean.store.Product;
import com.training.web_store.service.exception.ServiceException;

import java.util.List;

public interface SearchService {

    List<Product> getProducts(String request) throws ServiceException;

}
