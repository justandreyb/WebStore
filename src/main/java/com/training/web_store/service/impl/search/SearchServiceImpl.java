package com.training.web_store.service.impl.search;

import com.training.web_store.bean.store.Product;
import com.training.web_store.dao.PhotoDAO;
import com.training.web_store.dao.ProductDAO;
import com.training.web_store.dao.factory.DAOFactory;
import com.training.web_store.service.SearchService;
import com.training.web_store.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class SearchServiceImpl implements SearchService {
    private final DAOFactory factory = DAOFactory.getInstance();
    protected final ProductDAO productDAO = factory.getProductDAO();
    protected final PhotoDAO photoDAO = factory.getPhotoDAO();
    protected static final Logger log = Logger.getLogger(SearchServiceImpl.class.getName());

    @Override
    public List<Product> getProducts(String request) throws ServiceException {
        return null;
    }
}
