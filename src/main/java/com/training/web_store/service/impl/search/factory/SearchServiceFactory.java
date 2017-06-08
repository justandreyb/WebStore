package com.training.web_store.service.impl.search.factory;

import com.training.web_store.service.SearchService;
import com.training.web_store.service.impl.search.*;
import com.training.web_store.service.impl.search.impl.SearchByBrandService;
import com.training.web_store.service.impl.search.impl.SearchByCategoryService;
import com.training.web_store.service.impl.search.impl.SearchByNameService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SearchServiceFactory {

    private static final Logger log = Logger.getLogger(SearchServiceFactory.class.getName());
    private static final SearchServiceFactory factory = new SearchServiceFactory();

    private Map<SearchType, SearchService> serviceMap;

    private static final int INITIAL_AMOUNT = 3;

    private SearchServiceFactory() {
        serviceMap = new HashMap<SearchType, SearchService>(INITIAL_AMOUNT);

        serviceMap.put(SearchType.NAME, new SearchByNameService());
        serviceMap.put(SearchType.BRAND, new SearchByBrandService());
        serviceMap.put(SearchType.CATEGORY, new SearchByCategoryService());

        serviceMap.put(SearchType.WRONG, new WrongSearchService());
    }

    public static SearchServiceFactory getInstance() {
        return factory;
    }

    public SearchService getService(String type) {
        SearchType searchType;
        SearchService service;

        try {
            if (type != null) {
                searchType = SearchType.valueOf(type.toUpperCase());
                service = serviceMap.get(searchType);
            } else {
                service = serviceMap.get(SearchType.WRONG);
            }
        } catch (IllegalArgumentException ex) {
            service = serviceMap.get(SearchType.WRONG);
            log.debug("Wrong search type");
        }

        return service;
    }

}
