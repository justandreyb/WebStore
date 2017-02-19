package com.training.web_store.dao.impl;

import com.training.web_store.bean.User;
import com.training.web_store.dao.UserDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.ArgumentParserUtil;
import com.training.web_store.util.DBConnector;
import com.training.web_store.util.exception.UtilException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static final String USER_TABLE = "customer";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_LOGIN = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_PHONE = "phone_number";
    private static final String USER_GENDER = "gender";
    private static final String USER_ADDRESS = "address";
    private static final String USER_LOCALE = "locale";
    private static final String USER_ROLE = "role_id";
    private static final String USER_IS_BLOCKED = "is_blocked";

    private static final int ROLE_ID_FOR_USER = 2;
    private static final int NOT_BLOCKED_USER_VALUE = 0;

    @Override
    public void addUser(String login, String password, String firstName, String lastName, String gender,
                        String address, String phoneNumber, String locale) throws DAOException {
        String fields = USER_LOGIN + ", " + USER_PASSWORD + ", " + USER_FIRST_NAME + ", " +
                        USER_LAST_NAME + ", " + USER_PHONE + ", " + USER_ADDRESS + ", " +
                        USER_GENDER + ", " + USER_ROLE + ", " + USER_IS_BLOCKED + ", " +
                        USER_LOCALE;

        String query = "INSERT INTO " + USER_TABLE + " (" + fields + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnector.getConnection();

            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, phoneNumber);
            statement.setString(6, address);
            statement.setString(7, gender);
            statement.setInt(8, ROLE_ID_FOR_USER);
            statement.setInt(9, NOT_BLOCKED_USER_VALUE);
            statement.setString(10, locale);

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during adding new user");
            }

        } catch (UtilException | SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                DBConnector.closeConnection(connection);
            } catch (UtilException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public User getUser(String login, String password) throws DAOException {
        String fields = USER_FIRST_NAME + ", " + USER_LAST_NAME + ", " + USER_GENDER  + ", " +
                        USER_PHONE + ", " + USER_ADDRESS + ", " + USER_LOCALE;

        String query = "SELECT " + fields + " FROM " + USER_TABLE + " WHERE " + USER_LOGIN + "=? AND " + USER_PASSWORD + "=?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;

        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, login);
            statement.setString(2, password);

            set = statement.executeQuery();

            while (set.next()) {
                String firstName = set.getString(USER_FIRST_NAME);
                String lastName = set.getString(USER_LAST_NAME);
                String gender = set.getString(USER_GENDER);
                String address = set.getString(USER_ADDRESS);
                String phoneNumber = set.getString(USER_PHONE);
                String locale = set.getString(USER_LOCALE);

                if (ArgumentParserUtil.isValidArguments(firstName, lastName, gender, address, phoneNumber, locale)) {
                    user = new User(login, password, firstName, lastName, gender, address, phoneNumber, locale);
                }
            }
            return user;

        } catch (UtilException | SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                DBConnector.closeConnection(connection);
            } catch (UtilException e) {
                throw new DAOException(e);
            }
        }
    }
}
