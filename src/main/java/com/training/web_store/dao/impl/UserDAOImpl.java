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

    private static final String ADD_USER_QUERY =
            "INSERT INTO " + USER_TABLE + " (" +
                USER_LOGIN + ", " + USER_PASSWORD + ", " + USER_FIRST_NAME + ", " +
                USER_LAST_NAME + ", " + USER_PHONE + ", " + USER_ADDRESS + ", " +
                USER_GENDER + ", " + USER_ROLE + ", " + USER_IS_BLOCKED + ", " +
                USER_LOCALE + ") " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_QUERY =
            "SELECT " +
                USER_FIRST_NAME + ", " + USER_LAST_NAME + ", " + USER_GENDER  + ", " +
                USER_PHONE + ", " + USER_ADDRESS + ", " + USER_LOCALE +
            " FROM " + USER_TABLE +
            " WHERE " + USER_LOGIN + "=? AND " + USER_PASSWORD + "=?";

    private static final int ROLE_ID_FOR_USER = 2;
    private static final int NOT_BLOCKED_USER_VALUE = 0;

    @Override
    public void addUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnector.getConnection();

            statement = connection.prepareStatement(ADD_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getGender());
            statement.setInt(8, ROLE_ID_FOR_USER);
            statement.setInt(9, NOT_BLOCKED_USER_VALUE);
            statement.setString(10, user.getLocale());

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

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;

        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(GET_USER_QUERY);

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
