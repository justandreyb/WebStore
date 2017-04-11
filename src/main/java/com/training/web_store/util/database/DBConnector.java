package com.training.web_store.util.database;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

//TODO: Check and rework
public class DBConnector {
    private static final DBConnector instance = new DBConnector();

    private BlockingQueue<Connection> connections;
    private CopyOnWriteArrayList<Connection> usedConnections;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static Logger log = Logger.getLogger(DBConnector.class);

    private DBConnector() {

    }

    private void initializeProperties() /*throws ProjectUtilException*/ {
        DatabaseResourcesMapper resourcesMapper = DatabaseResourcesMapper.getInstance();

        this.driverName = resourcesMapper.getProperty("db.driver");
        this.url = resourcesMapper.getProperty("db.url");
        this.user = resourcesMapper.getProperty("db.user");
        this.password = resourcesMapper.getProperty("db.password");
        try {
            this.poolSize = Integer.parseInt(resourcesMapper.getProperty("db.poolSize"));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
    }

    public static DBConnector getInstance() {
        return instance;
    }

    public void init() /*throws ProjectUtilException*/ {
        initializeProperties();

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException exception) {
            log.fatal("JDBC error : ", exception);
//            throw new ProjectUtilException("Problem with connection to JDBC", exception);
        }
        connections = new ArrayBlockingQueue<Connection>(poolSize);
        usedConnections = new CopyOnWriteArrayList<Connection>();

        for (int i = 0; i < poolSize; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException exception) {
                log.fatal("JDBC error : ", exception);
//                throw new ProjectUtilException("Problem with establishing connection", exception);
                return;
            }
            connections.add(connection);
        }
        log.info("Connection initialized.");
    }

    public void destroy() /*throws ProjectUtilException*/ {

        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException exception) {
                log.warn("Problem with closing connection : ", exception);
                //       TODO:     throw new ProjectUtilException(e);
            }
        }

        for (Connection connection : usedConnections) {
            try {
                connection.close();
            } catch (SQLException exception) {
                log.warn("Problem with closing connection : ", exception);
            }
        }
    }

    public Connection getConnection() /*throws ProjectUtilException*/ {
        Connection connection = null;
        try {
            connection = connections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
//       TODO:     throw new ProjectUtilException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) /*throws ProjectUtilException*/ {
        if (connection != null) {
            connections.add(connection);
            usedConnections.remove(connection);
        } else {
            log.warn("Null connection returned.");
        }
    }

    public void closeConnection(Connection connection, Statement statement) /*throws ProjectUtilException*/ {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //       TODO:     throw new ProjectUtilException(e);
        }
        closeConnection(connection);
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet set)/* throws ProjectUtilException*/ {
        try {
            if (set != null) {
                set.close();
            }
        } catch (SQLException e) {
            //       TODO:     throw new ProjectUtilException(e);
        }
        closeConnection(connection, statement);
    }

}
