package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Product;
import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.ProductDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.ArgumentExchanger;
import com.training.web_store.util.database.DBConnector;
import com.training.web_store.util.exception.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class ProductDAOImpl implements ProductDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String ID = "id";
    private static final String DATABASE = "web_store";

    private static final String PRODUCT_TABLE = "product";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_IS_AVAILABLE = "is_available";

    private static final String DISCOUNT_TABLE = "discount";
    private static final String DISCOUNT_TABLE_VALUE = "value";
    private static final String DISCOUNT_ID = "discount_id";
    private static final String DISCOUNT_VALUE = "discount";

    private static final String CATEGORY_TABLE = "category";
    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category";
    private static final String CATEGORY_TABLE_NAME = "name";

    private static final String THING_TO_PRODUCT_TABLE = "thing_to_product";
    private static final String PRODUCT_ID = "product_id";
    private static final String THING_ID = "thing_id";
    private static final String THING_TO_PRODUCT_AMOUNT = "amount";
    private static final String THING_BRAND = "brand";

    private static final String ADD_PRODUCT_QUERY =
            "INSERT INTO " + DATABASE + "." + PRODUCT_TABLE + " (" +
                PRODUCT_NAME + ", " +
                PRODUCT_PRICE + ", " +
                CATEGORY_ID + ", " +
                DISCOUNT_ID +
            ") " +
            "VALUES (?, ?, ?, ?)";

    private static final String GET_PRODUCT_QUERY = "{call getProduct(?)}";

    private static final String GET_PRODUCTS_QUERY = "{call getProducts()}";

    private static final String GET_PRODUCTS_FOR_CATEGORY_QUERY = "{call getProductsForCategory(?)}";

    private static final String GET_PRODUCTS_FOR_BRAND_QUERY = "{call getProductsForBrand(?)}";

    private static final String GET_PRODUCTS_FOR_DISCOUNT_QUERY = "{call getProductsForDiscount(?)}";

    private static final String GET_PRODUCTS_FOR_ORDER_QUERY = "{call getProductsForOrder(?)}";

    private static final String SEARCH_PRODUCT_QUERY = "{call searchProduct(?)}";

    private static final String SEARCH_PRODUCT_BY_CATEGORY_QUERY =
            "SELECT " +
                PRODUCT_TABLE + "." + ID + ", " +
                PRODUCT_TABLE + "." + PRODUCT_NAME + ", " +
                PRODUCT_TABLE + "." + PRODUCT_PRICE + ", " +
                DISCOUNT_TABLE + "." + DISCOUNT_TABLE_VALUE + " AS " + DISCOUNT_VALUE +
            " FROM " +
                DATABASE + "." + PRODUCT_TABLE +
                " LEFT JOIN " + DATABASE + "." + DISCOUNT_TABLE + " ON " +
                    DISCOUNT_TABLE + "." + ID + " = " + PRODUCT_TABLE + "." + DISCOUNT_ID +
                " LEFT JOIN " + DATABASE + "." + CATEGORY_TABLE + " ON " +
                    CATEGORY_TABLE + "." + ID + " = " + PRODUCT_TABLE + "." + CATEGORY_ID +
            " WHERE " +
                CATEGORY_TABLE + "." + CATEGORY_TABLE_NAME + " LIKE CONCAT('%', ?, '%') AND " +
                PRODUCT_TABLE + "." + PRODUCT_IS_AVAILABLE + " = 1";

    private static final String UPDATE_PRODUCT_QUERY =
            "UPDATE " + DATABASE + "." + PRODUCT_TABLE +
            " SET " +
                PRODUCT_NAME + "=?, " +
                PRODUCT_PRICE + "=?, " +
                CATEGORY_ID + "=?, " +
                DISCOUNT_ID + "=?" +
            " WHERE " +
                ID + "=?";

    private static final String SET_PRODUCT_AVAILABLE =
            "UPDATE " + DATABASE + "." + PRODUCT_TABLE +
            " SET " +
                PRODUCT_IS_AVAILABLE + "=?" +
            " WHERE " +
                ID + "=?";

    private static final String ADD_THING_QUERY =
            "INSERT INTO " + DATABASE + "." + THING_TO_PRODUCT_TABLE + " (" +
                    PRODUCT_ID + ", " +
                    THING_ID + ", " +
                    THING_TO_PRODUCT_AMOUNT +
                    ") " +
                    "VALUES (?, ?, ?)";

    private static final String GET_THINGS_QUERY = "{call getThingsForProduct(?)}";

    private static final String REMOVE_THING_FROM_PRODUCT_QUERY =
            "DELETE FROM " + DATABASE + "." + THING_TO_PRODUCT_TABLE +
            " WHERE " +
                THING_ID + "=? AND " +
                PRODUCT_ID + "=?"
            ;

    private static final String ERROR_ADDING = "Error during adding new entity";

    private static final String ERROR_DELETING = "Error during deleting";
    private static final int INITIAL_AMOUNT = 1;

    @Override
    public void addProduct(String name, double price, int categoryId) throws DAOException, StorageException {
        addProduct(name, price, categoryId, 0);
    }

    @Override
    public void addProduct(String name, double price, int categoryId, int discountId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_PRODUCT_QUERY);

            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, categoryId);
            if (discountId != 0) {
                statement.setInt(4, discountId);
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_ADDING);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public void updateProduct(int productId, String name, double price, int categoryId) throws DAOException, StorageException {
        updateProduct(productId, name, price, categoryId, 0);
    }

    @Override
    public void updateProduct(int productId, String name, double price, int categoryId, int discountId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE_PRODUCT_QUERY);

            statement.setInt(5, productId);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, categoryId);
            if (discountId != 0) {
                statement.setInt(4, discountId);
            } else {
                statement.setNull(4, Types.INTEGER);
            }

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Something went wrong. Product wasn't updated");
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection);
        }
    }

    @Override
    public Product getProduct(int productId) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        Product product = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_PRODUCT_QUERY);

            statement.setInt(1, productId);

            set = statement.executeQuery();

            while (set.next()) {
                product = new Product();

                product.setId(productId);

                String name = set.getString(PRODUCT_NAME);
                double price = set.getDouble(PRODUCT_PRICE);
                byte discount = set.getByte(DISCOUNT_VALUE);
                String category = set.getString(CATEGORY_NAME);

                product.setName(name);
                product.setPrice(price);
                product.setDiscount(discount);
                product.setCategory(category);
            }
            return product;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public Product getProduct(String name, int categoryId) throws DAOException {
        return null;
    }

    @Override
    public List<Product> getProducts() throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Product> products = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_PRODUCTS_QUERY);

            set = statement.executeQuery();

            products = new LinkedList<Product>();

            while (set.next()) {
                Product product = new Product();

                int productId = set.getInt(ID);
                String name = set.getString(PRODUCT_NAME);
                double price = set.getDouble(PRODUCT_PRICE);
                byte discount = set.getByte(DISCOUNT_VALUE);
                String category = set.getString(CATEGORY_NAME);

                product.setId(productId);
                product.setName(name);
                product.setPrice(price);
                product.setDiscount(discount);
                product.setCategory(category);

                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Product> searchProduct(String requestedName) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Product> products = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(SEARCH_PRODUCT_QUERY);

            statement.setString(1, requestedName);

            set = statement.executeQuery();

            products = new LinkedList<Product>();

            while (set.next()) {
                Product product = new Product();

                int productId = set.getInt(ID);
                String name = set.getString(PRODUCT_NAME);
                double price = set.getDouble(PRODUCT_PRICE);
                byte discount = set.getByte(DISCOUNT_VALUE);

                product.setId(productId);
                product.setName(name);
                product.setPrice(price);
                product.setDiscount(discount);

                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Product> searchProductByBrand(String brandName) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Product> products = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_PRODUCTS_QUERY);

            set = statement.executeQuery();

            products = new LinkedList<Product>();

            while (set.next()) {
                Product product = new Product();

                int productId = set.getInt(ID);
                String name = set.getString(PRODUCT_NAME);
                double price = set.getDouble(PRODUCT_PRICE);
                byte discount = set.getByte(DISCOUNT_VALUE);
                String category = set.getString(CATEGORY_NAME);

                product.setId(productId);
                product.setName(name);
                product.setPrice(price);
                product.setDiscount(discount);
                product.setCategory(category);

                products.add(product);
            }

            List<Product> targetProducts = new ArrayList<>();
            for (Product product : products) {
                if (analyzeProductOnTargetBrand(product, brandName)) {
                    targetProducts.add(product);
                }
            }

            return targetProducts;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    private boolean analyzeProductOnTargetBrand(Product product, String targetName) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_THINGS_QUERY);

            statement.setInt(1, product.getId());

            set = statement.executeQuery();

            boolean result = false;

            while (set.next()) {
                String brand = set.getString(THING_BRAND);
                brand = brand.toLowerCase();
                if (brand.contains(targetName.toLowerCase())) {
                    result = true;
                }
            }

            return result;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Product> searchProductByCategory(String categoryName) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Product> products = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SEARCH_PRODUCT_BY_CATEGORY_QUERY);

            statement.setString(1, categoryName);

            set = statement.executeQuery();

            products = new LinkedList<Product>();

            while (set.next()) {
                Product product = new Product();

                int productId = set.getInt(ID);
                String name = set.getString(PRODUCT_NAME);
                double price = set.getDouble(PRODUCT_PRICE);
                byte discount = set.getByte(DISCOUNT_VALUE);

                product.setId(productId);
                product.setName(name);
                product.setPrice(price);
                product.setDiscount(discount);

                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Product> getProductsForCategory(int categoryId) throws DAOException, StorageException {
        return getProductsToRequirements(GET_PRODUCTS_FOR_CATEGORY_QUERY, categoryId);
    }

    @Override
    public List<Product> getProductsForBrand(int brandId) throws DAOException, StorageException {
        return getProductsToRequirements(GET_PRODUCTS_FOR_BRAND_QUERY, brandId);
    }

    @Override
    public List<Product> getProductsForDiscount(int discountId) throws DAOException, StorageException {
        return getProductsToRequirements(GET_PRODUCTS_FOR_DISCOUNT_QUERY, discountId);
    }

    @Override
    public List<Product> getProductsForOrder(int orderId) throws DAOException, StorageException {
        return getProductsToRequirements(GET_PRODUCTS_FOR_ORDER_QUERY, orderId);
    }

    private List<Product> getProductsToRequirements(String query, int entityId) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Product> products = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(query);

            statement.setInt(1, entityId);

            set = statement.executeQuery();

            products = new LinkedList<Product>();

            while (set.next()) {
                Product product = new Product();

                int productId = set.getInt(ID);
                String name = set.getString(PRODUCT_NAME);
                double price = set.getDouble(PRODUCT_PRICE);
                byte discount = set.getByte(DISCOUNT_VALUE);

                product.setId(productId);
                product.setName(name);
                product.setPrice(price);
                product.setDiscount(discount);

                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void setProductAvailable(int productId, boolean available) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_PRODUCT_AVAILABLE);
            statement.setBoolean(1, available);
            statement.setInt(2, productId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_DELETING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void addThing(int productId, int thingId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_THING_QUERY);
            statement.setInt(1, productId);
            statement.setInt(2, thingId);
            statement.setInt(3, INITIAL_AMOUNT);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_ADDING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void removeThing(int productId, int thingId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(REMOVE_THING_FROM_PRODUCT_QUERY);
            statement.setInt(1, thingId);
            statement.setInt(2, productId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_DELETING);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }
}
