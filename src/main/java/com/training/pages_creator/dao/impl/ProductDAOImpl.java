package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;

import java.util.List;

public class ProductDAOImpl implements EntityDAO {
    private static final String DATABASE = "web_store";
    private static final String PRODUCT_TABLE = "product";
    private static final String ID = "id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";
    private static final String DISCOUNT_ID = "discount_id";
    private static final String CATEGORY_ID = "category_id";
    private static final String PRODUCT_IS_AVAILABLE = "is_available";

    private static final String THING_TO_PRODUCT_TABLE = "thing_to_product";
    private static final String PRODUCT_ID = "product_id";
    private static final String THING_ID = "thing_id";
    private static final String THING_TO_PRODUCT_AMOUNT = "amount";

    private static final String THING_TABLE = "thing";
    private static final String THING_NAME = "name";
    private static final String THING_DESCRIPTION = "description";
    private static final String THING_CREATION_DATE = "creation_date";
    private static final String THING_REVIEW = "review";
    private static final String BRAND_ID = "brand_id";

    @Override
    public CollectedData getObject(int productId) throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        return null;
    }

    @Override
    public List<CollectedData> getObjects(int objectId) throws DAOException {
        return null;
    }

}
