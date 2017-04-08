package com.training.pages_creator.dao.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;
import com.training.util.database.DBConnector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class AccountDAOImpl implements EntityDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String USER_ID = "id";
    private static final String USER_LOGIN = "email";
    private static final String USER_FIRST_NAME = "first_name";

    private static final String USER_PREFIX = "user_";

    private static final String GET_USER_QUERY = "{call getUser(?)}";
    private static final String GET_USERS_QUERY = "{call getUsers()}";

    @Override
    public CollectedData getObject(int objectId) throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        CollectedData data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_USER_QUERY);

            statement.setInt(1, objectId);

            set = statement.executeQuery();

            data = new CollectedData();

            while (set.next()) {
                int userId = set.getInt(USER_ID);
                String login = set.getString(USER_LOGIN);
                String firstName = set.getString(USER_LOGIN);

                data.addObject(USER_PREFIX + USER_ID, String.valueOf(userId));
                data.addObject(USER_PREFIX + USER_LOGIN, login);
                data.addObject(USER_PREFIX + USER_FIRST_NAME, firstName);
            }
            return data;

        } catch (SQLException /*| ProjectUtilException */e) {
            throw new DAOException(e);
        } finally {
//            try {
            dbConnector.closeConnection(connection, statement, set);
            /*} catch (ProjectUtilException e) {
                throw new DAOException(e);
            }*/
        }
    }

    @Override
    public List<CollectedData> getObjects() throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<CollectedData> data = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_USERS_QUERY);
            set = statement.executeQuery();

            data = new ArrayList<CollectedData>();

            while (set.next()) {
                CollectedData collectedData = new CollectedData();

                int userId = set.getInt(USER_ID);
                String login = set.getString(USER_LOGIN);
                String firstName = set.getString(USER_FIRST_NAME);

                collectedData.addObject(USER_PREFIX + USER_ID, String.valueOf(userId));
                collectedData.addObject(USER_PREFIX + USER_LOGIN, login);
                collectedData.addObject(USER_PREFIX + USER_FIRST_NAME, firstName);

                data.add(collectedData);
            }
            return data;

        } catch (SQLException/* | ProjectUtilException*/ e) {
            throw new DAOException(e);
        } finally {
//            try {
            dbConnector.closeConnection(connection, statement, set);
            /*} catch (ProjectUtilException e) {
                throw new DAOException(e);
            }*/
        }
    }

    @Override
    public List<CollectedData> getObjects(int objectId) throws DAOException {
        return null;
    }
}
