package com.training.util.database;

import com.training.web_store.dao.exception.DAOException;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

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

    private void initializeProperties() throws DAOException {
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

   /* public Connection getConnection() throws DAOException {
        try {
            Class.forName(driverName);
            try {
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException sqlEx) {
                log.log(Level.SEVERE, "Cannot connect to sql server", sqlEx);
                throw new DAOException(sqlEx);
            }
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "DB driver not found", e);
            throw new DAOException(e);
        }
    }

    public static void closeConnection(Connection connection) throws ProjectUtilException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error while close connection to DB", e);
            throw new ProjectUtilException(e.getMessage());
        }
    }
*/
    /*=========================================================*/

    public static DBConnector getInstance() {
        return instance;
    }

    public void init() throws DAOException {
        initializeProperties();

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException exception) {
            log.fatal("JDBC error : ", exception);
            throw new DAOException("Problem with connection to JDBC", exception);
        }
        connections = new ArrayBlockingQueue<Connection>(poolSize);
        usedConnections = new CopyOnWriteArrayList<Connection>();

        for (int i = 0; i < poolSize; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException exception) {
                log.fatal("JDBC error : ", exception);
                throw new DAOException("Problem with establishing connection", exception);
            }
            connections.add(connection);
        }
        log.info("Connection initialized.");
    }

    public void destroy() throws DAOException {

        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException exception) {
                log.warn("Problem with closing connection : ", exception);
                throw new DAOException(exception);
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

    public Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            connection = connections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new DAOException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws DAOException {
        if (connection != null) {
            connections.add(connection);
            usedConnections.remove(connection);
        } else {
            log.warn("Null connection returned.");
        }
    }

    public void closeConnection(Connection connection, Statement statement) throws DAOException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        closeConnection(connection);
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet set) throws DAOException {
        try {
            if (set != null) {
                set.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        closeConnection(connection, statement);
    }

}
