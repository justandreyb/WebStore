package com.training.web_store.util.database;

import com.training.web_store.util.exception.StorageException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private void initializeProperties() throws StorageException {
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

    public void init() throws StorageException {
        initializeProperties();

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException exception) {
            log.fatal("JDBC error : ", exception);
            throw new StorageException("Problem with connection to JDBC", exception);
        }
        connections = new ArrayBlockingQueue<Connection>(poolSize);
        usedConnections = new CopyOnWriteArrayList<Connection>();

        for (int i = 0; i < poolSize; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException exception) {
                log.fatal("JDBC error : ", exception);
                throw new StorageException("Problem with establishing connection", exception);
            }
            connections.add(connection);
        }
        log.debug("Connection initialized.");
    }

    public void destroy() throws StorageException {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException exception) {
                log.warn("Problem with closing connection : ", exception);
                throw new StorageException(exception);
            }
        }
    }

    public Connection getConnection() throws StorageException {
        Connection connection = null;
        try {
            connection = connections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new StorageException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws StorageException {
        if (connection != null) {
            connections.add(connection);
            usedConnections.remove(connection);
        } else {
            log.warn("Null connection returned.");
        }
    }

    public void closeConnection(Connection connection, Statement statement) throws StorageException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        closeConnection(connection);
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet set) throws StorageException {
        try {
            if (set != null) {
                set.close();
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        closeConnection(connection, statement);
    }

}
