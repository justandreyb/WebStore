package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Brand;
import com.training.web_store.dao.BrandDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.database.DBConnector;
import com.training.web_store.util.exception.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class BrandDAOImpl implements BrandDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String BRAND_TABLE = "brand";
    private static final String BRAND_ID = "id";
    private static final String BRAND_NAME = "name";
    private static final String BRAND_IS_AVAILABLE = "is_available";
    private static final String BRAND_DESCRIPTION = "description";

    private static final String EMPTY_DESCRIPTION = null;

    private static final String ADD_BRAND_QUERY =
            "INSERT INTO " + DATABASE + "." + BRAND_TABLE + " (" +
                BRAND_NAME + ", " +
                BRAND_DESCRIPTION +
            ") " +
            "VALUES (?, ?)";

    private static final String UPDATE_BRAND_QUERY =
            "UPDATE " + DATABASE + "." + BRAND_TABLE +
            " SET " +
                BRAND_NAME + "=?, " +
                BRAND_DESCRIPTION + "=?" +
            " WHERE " +
                BRAND_ID + "=?";

    private static final String GET_BRAND_QUERY = "{call getBrand(?)}";

    private static final String GET_BRANDS_QUERY = "{call getBrands()}";

    private static final String SET_BRAND_AVAILABLE =
            "UPDATE " + DATABASE + "." + BRAND_TABLE +
            " SET " +
                BRAND_IS_AVAILABLE + "=?" +
            " WHERE " +
                BRAND_ID + "=?";

    private static final String ERROR_ADDING = "Error during adding new entity";
    private static final String ERROR_UPDATE = "Error during updating";
    private static final String ERROR_DELETING = "Error during changing state";

    @Override
    public void addBrand(String name) throws DAOException, StorageException {
        addBrand(name, EMPTY_DESCRIPTION);
    }

    @Override
    public void addBrand(String name, String description) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_BRAND_QUERY);

            statement.setString(1, name);
            if (description != null) {
                statement.setString(2, description);
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
    public void updateBrand(int brandId, String name, String updatedDescription) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE_BRAND_QUERY);

            statement.setString(1, name);
            statement.setString(2, updatedDescription);
            statement.setInt(3, brandId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_UPDATE);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public Brand getBrand(int brandId) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        Brand brand = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_BRAND_QUERY);

            statement.setInt(1, brandId);

            set = statement.executeQuery();


            while (set.next()) {
                brand = new Brand();

                brand.setId(brandId);

                String name = set.getString(BRAND_NAME);
                String description = set.getString(BRAND_DESCRIPTION);

                brand.setName(name);
                brand.setDescription(description);

            }
            return brand;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Brand> getBrands() throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Brand> brands = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_BRANDS_QUERY);
            set = statement.executeQuery();

            brands = new ArrayList<Brand>();

            while (set.next()) {
                Brand brand = new Brand();

                int brandId = set.getInt(BRAND_ID);
                String name = set.getString(BRAND_NAME);

                brand.setId(brandId);
                brand.setName(name);

                brands.add(brand);
            }
            return brands;

        } catch (SQLException e) {
            throw new DAOException("",e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void setBrandAvailable(int brandId, boolean available) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_BRAND_AVAILABLE);
            statement.setBoolean(1, available);
            statement.setInt(2, brandId);

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
