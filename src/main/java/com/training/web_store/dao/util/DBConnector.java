package com.training.web_store.dao.util;

import com.training.web_store.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static Logger log = Logger.getLogger(DBConnector.class.getName());

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
            log.log(Level.SEVERE, "JDBC error : ", exception);
            throw new DAOException("Problem with connection to JDBC", exception);
        }
        connections = new ArrayBlockingQueue<Connection>(poolSize);
        usedConnections = new CopyOnWriteArrayList<Connection>();

        for (int i = 0; i < poolSize; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException exception) {
                log.log(Level.SEVERE, "JDBC error : ", exception);
                throw new DAOException("Problem with establishing connection", exception);
            }
            connections.add(connection);
        }
        log.log(Level.INFO,"Connection initialized.");
    }

    public void destroy() {

        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException exception) {
                log.log(Level.SEVERE, "Problem with closing connection : ", exception);
            }
        }

        for (Connection connection : usedConnections) {
            try {
                connection.close();
            } catch (SQLException exception) {
                log.log(Level.SEVERE, "Problem with closing connection : ", exception);
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
            log.log(Level.SEVERE, "Null connection returned.");
        }
    }

}
