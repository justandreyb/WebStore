package com.training.web_store.dao.util;

import com.training.web_store.bean.BeanContainer;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementExecutor {

    private static final DBConnector dbConnector = DBConnector.getInstance();

    public static void executeAddQuery(String query, BeanContainer container) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();

            connection.setAutoCommit(false);

            statement = connection.prepareStatement(query);


            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during adding new user");
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
            dbConnector.closeConnection(connection);
        }
    }

    public static void executeGetQuery(String query, BeanContainer container) {

    }

    public static void executeUpdateQuery(String query, BeanContainer container) {

    }

    public static void executeSetAvailableQuery(String query, BeanContainer container) {

    }
}
