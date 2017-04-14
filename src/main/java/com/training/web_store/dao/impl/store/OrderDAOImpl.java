package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Order;
import com.training.web_store.dao.OrderDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.ArgumentExchanger;
import com.training.web_store.util.database.DBConnector;
import com.training.web_store.util.exception.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class OrderDAOImpl implements OrderDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String ORDER_TABLE = "order";
    private static final String ORDER_ID = "id";
    private static final String ORDER_PRICE = "price";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String ORDER_IS_COMPLETE = "is_complete";
    private static final String ORDER_CREATION_DATE = "creation_date";

    private static final String ADD_ORDER_QUERY =
            "INSERT INTO " + DATABASE + "." + ORDER_TABLE + " (" +
                ORDER_PRICE + ", " +
                ORDER_CREATION_DATE + ", " +
                ORDER_IS_COMPLETE +
            ") " +
            "VALUES (?, ?, ?)";

    private static final String GET_ORDER_QUERY =
            "SELECT " +
                ORDER_PRICE + ", " +
                ORDER_CREATION_DATE + ", " +
                ORDER_IS_COMPLETE +
            " FROM " +
                DATABASE + "." + ORDER_TABLE +
            " WHERE " +
                CUSTOMER_ID + "=? AND" +
                ORDER_ID + "=?"
            ;

    private static final String GET_ORDERS_QUERY = "{call getOrders(?)}";
            /*"SELECT " +
                ORDER_ID + ", " +
                ORDER_PRICE + ", " +
                ORDER_CREATION_DATE + ", " +
                ORDER_IS_COMPLETE +
            " FROM " +
                DATABASE + "." + ORDER_TABLE +
            " WHERE " +
                CUSTOMER_ID + "=?";*/

    private static final String SET_ORDER_STATE =
            "UPDATE " + DATABASE + "." + ORDER_TABLE +
            " SET " +
                ORDER_IS_COMPLETE + "=?" +
            " WHERE " +
                ORDER_ID + "=?";

    private static final String ERROR_ADDING = "Error during adding new entity";
    private static final String ERROR_DELETING = "Error during deleting";

    @Override
    public void addOrder(Order order) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(ADD_ORDER_QUERY);

            statement.setDouble(1, order.getPrice());
            //TODO: Replace
            statement.setDate(2, ArgumentExchanger.exchangeToSQLDate(order.getCreationDate()));
            statement.setBoolean(3, order.isComplete());

            if (statement.executeUpdate() < 1) {
                throw new DAOException(ERROR_ADDING);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public Order getOrder(int userId, int orderId) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_ORDER_QUERY);

            statement.setInt(1, userId);
            statement.setInt(2, orderId);

            set = statement.executeQuery();

            Order order = null;

            while (set.next()) {
                order = new Order();

                boolean complete = set.getBoolean(ORDER_IS_COMPLETE);
                double price = set.getDouble(ORDER_PRICE);
                //TODO: Replace
                java.util.Date creationDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(ORDER_CREATION_DATE));

                order.setId(orderId);
                order.setCustomerId(userId);
                order.setCreationDate(creationDate);
                order.setPrice(price);
                order.setComplete(complete);
            }
            return order;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<Order> getOrders(int userId) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<Order> orders = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_ORDERS_QUERY);

            statement.setInt(1, userId);

            set = statement.executeQuery();

            orders = new ArrayList<Order>();

            while (set.next()) {
                Order order = new Order();

                int orderId = set.getInt(ORDER_ID);
                boolean complete = set.getBoolean(ORDER_IS_COMPLETE);
                double price = set.getDouble(ORDER_PRICE);
                //TODO: Replace
                java.util.Date creationDate = ArgumentExchanger.exchangeFromSQLDate(set.getDate(ORDER_CREATION_DATE));

                order.setId(orderId);
                order.setCustomerId(userId);
                order.setCreationDate(creationDate);
                order.setPrice(price);
                order.setComplete(complete);

                orders.add(order);
            }
            return orders;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void setOrderState(int orderId, boolean state) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_ORDER_STATE);
            statement.setBoolean(1, state);
            statement.setInt(2, orderId);

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
