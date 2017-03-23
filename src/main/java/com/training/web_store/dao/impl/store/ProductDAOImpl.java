package com.training.web_store.dao.impl.store;

import com.training.web_store.dao.ProductDAO;
import com.training.web_store.bean.store.Product;
import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
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

    private static final int PRODUCT_AVAILABLE = 1;

    private static final String ADD_PRODUCT_QUERY =
            "INSERT INTO " + DATABASE + "." + PRODUCT_TABLE + " (" +
                PRODUCT_NAME + ", " +
                PRODUCT_PRICE + ", " +
                DISCOUNT_ID + ", " +
                CATEGORY_ID +
            ") " +
            "VALUES (?, ?, ?, ?)";

    private static final String ADD_THING_QUERY =
            "INSERT INTO " + DATABASE + "." + THING_TO_PRODUCT_TABLE + " (" +
                PRODUCT_ID + ", " +
                THING_ID + ", " +
                THING_TO_PRODUCT_AMOUNT +
            ") " +
            "VALUES (?, ?, ?)";

    private static final String UPDATE_PRODUCT_QUERY =
            "UPDATE " + DATABASE + "." + PRODUCT_TABLE +
            " SET " +
                PRODUCT_NAME + "=?, " +
                PRODUCT_PRICE + "=?, " +
                DISCOUNT_ID + "=?, " +
                CATEGORY_ID + "=?" +
            " WHERE " +
                ID + "=?";

    private static final String GET_PRODUCT_QUERY =
            "SELECT " +
                PRODUCT_NAME + ", " +
                PRODUCT_PRICE +
            " FROM " +
                DATABASE + "." + PRODUCT_TABLE +
            " WHERE " +
                ID + "=? AND" +
                PRODUCT_IS_AVAILABLE + "=" + PRODUCT_AVAILABLE;

    //Created
    private static final String GET_PRODUCTS_BY_CATEGORY_QUERY =
            "SELECT " +
                ID + ", " +
                PRODUCT_NAME + ", " +
                PRODUCT_PRICE +
            " FROM " +
                DATABASE + "." + PRODUCT_TABLE +
            " WHERE " +
                CATEGORY_ID + "=?";

    //Created
    private static final String GET_PRODUCTS_BY_SEARCH_QUERY =
            "SELECT " +
                ID + ", " +
                PRODUCT_NAME + ", " +
                PRODUCT_PRICE +
            " FROM " +
                DATABASE + "." + PRODUCT_TABLE +
            " WHERE " +
                PRODUCT_NAME + " LIKE ?";


    //Created
    private static final String GET_THINGS_QUERY =
            "SELECT " +
                THING_TO_PRODUCT_TABLE + "." + THING_TO_PRODUCT_AMOUNT + ", " +
                THING_TABLE + "." + ID + ", " +
                THING_TABLE + "." + THING_NAME + ", " +
                THING_TABLE + "." + THING_DESCRIPTION + ", " +
                THING_TABLE + "." + THING_CREATION_DATE + ", " +
                THING_TABLE + "." + THING_REVIEW + ", " +
                THING_TABLE + "." + BRAND_ID +
            " FROM " +
                DATABASE + "." + THING_TO_PRODUCT_TABLE +
            " INNER JOIN " + DATABASE + "." + THING_TABLE + " ON " +
                    THING_TABLE + "." + ID + "=" + THING_TO_PRODUCT_TABLE + "." + THING_ID +
            " WHERE " +
                THING_TO_PRODUCT_TABLE + "." + PRODUCT_ID + "=?";

    private static final String SET_PRODUCT_AVAILABLE =
            "UPDATE " + DATABASE + "." + PRODUCT_TABLE +
            " SET " +
                PRODUCT_IS_AVAILABLE + "=?" +
            " WHERE " +
                ID + "=?";

    private static final String REMOVE_THING_FROM_PRODUCT_QUERY = "" +
            "DELETE FROM " + DATABASE + "." + THING_TO_PRODUCT_TABLE +
            " WHERE " + THING_ID + "=?";


    @Override
    public void addProduct(String name, double price, int categoryId) throws DAOException {

    }

    @Override
    public void addProduct(String name, double price, int categoryId, int discountId) throws DAOException {

    }

    @Override
    public void updateProduct(int productId, String name, double price, int categoryId, int discountId) throws DAOException {

    }

    @Override
    public void addThing(int productId, int thingId) throws DAOException {

    }

    @Override
    public Product getProduct(String name, int categoryId) throws DAOException {
        return null;
    }

    @Override
    public List<Product> getProducts(String requestedName) throws DAOException {
        //todo: don't forget about '%name%'
        return null;
    }

    @Override
    public List<Product> getProducts(int categoryId) throws DAOException {
        return null;
    }

    @Override
    public List<Thing> getThings(int categoryId) throws DAOException {
        return null;
    }

    @Override
    public void setProductAvailable(int productId, boolean available) throws DAOException {

    }

    @Override
    public void removeThing(int productId, int thingId) throws DAOException {

    }
}
