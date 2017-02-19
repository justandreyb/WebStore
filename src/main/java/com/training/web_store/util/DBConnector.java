package com.training.web_store.util;

import com.training.web_store.util.exception.UtilException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {
    private static Logger log = Logger.getLogger(DBConnector.class.getName());
    private static final String url = "jdbc:mysql://localhost:3306/web_store?autoReconnect=true&useSSL=false&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "123456";

    public static Connection getConnection() throws UtilException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException sqlEx) {
                log.log(Level.SEVERE, "Cannot connect to sql server", sqlEx);
                throw new UtilException(sqlEx);
            }
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "DB driver not found", e);
            throw new UtilException(e);
        }
    }

    public static void closeConnection(Connection connection) throws UtilException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error while close connection to DB", e);
            throw new UtilException(e.getMessage());
        }
    }

}
