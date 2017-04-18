package com.training.web_store.dao.impl.account;

import com.training.web_store.bean.account.User;
import com.training.web_store.dao.UserDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.database.DBConnector;
import com.training.web_store.util.exception.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String USER_TABLE = "user";
    private static final String USER_ID = "id";
    private static final String USER_LOGIN = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_IS_ACTIVE = "is_active";
    private static final String USER_LOCALE = "locale";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_PHONE = "phone_number";
    private static final String USER_GENDER = "gender";
    private static final String USER_ROLE_INFO = "role";
    private static final String USER_ADDRESS = "address";

    private static final String ADD_USER_QUERY = "{call addUser(?,?,?,?,?,?,?,?,?)}";

    private static final String GET_USER_QUERY = "{call getUser(?,?)}";

    private static final String SET_USER_AVAILABLE_QUERY =
            "UPDATE " + DATABASE + "." + USER_TABLE +
            " SET " + USER_IS_ACTIVE + "=?" +
            " WHERE " +
                USER_LOGIN + "=? AND" +
                USER_PASSWORD + "=?";

    private static final String UPDATE_USER_QUERY = "{call updateUser(?,?,?,?,?,?,?,?,?)}";

    private static final String GET_USERS_QUERY = "{call getUsers()}";
    private static final int ROLE_ID_FOR_USER = 1;

    @Override
    public void addUser(User user) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(ADD_USER_QUERY);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLocale());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getGender());
            statement.setString(7, user.getPhoneNumber());
            statement.setString(8, user.getAddress());
            statement.setInt(9, ROLE_ID_FOR_USER);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public void updateUser(int userId, User user) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(UPDATE_USER_QUERY);

            statement.setInt(1, userId);
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getLocale());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getGender());
            statement.setString(8, user.getPhoneNumber());
            statement.setString(9, user.getAddress());

            if (statement.executeUpdate() < 1) {
                throw new DAOException("User hasn't be update");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement);
        }
    }

    @Override
    public User getUser(String login, String password) throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_USER_QUERY);

            statement.setString(1, login);
            statement.setString(2, password);

            set = statement.executeQuery();

            User user = null;

            while (set.next()) {
                int userId = set.getInt(USER_ID);
                String firstName = set.getString(USER_FIRST_NAME);
                String lastName = set.getString(USER_LAST_NAME);
                String gender = set.getString(USER_GENDER);
                String phone = set.getString(USER_PHONE);
                String address = set.getString(USER_ADDRESS);
                String locale = set.getString(USER_LOCALE);
                String role = set.getString(USER_ROLE_INFO);


                user = new User(login, password, firstName, lastName, gender, address,
                        phone, locale);
                user.setId(userId);
                user.setRole(role);

            }
            return user;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public List<User> getUsers() throws DAOException, StorageException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet set = null;
        List<User> users = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareCall(GET_USERS_QUERY);
            set = statement.executeQuery();

            users = new ArrayList<User>();

            while (set.next()) {
                User user = new User();

                int userId = set.getInt(USER_ID);
                String email = set.getString(USER_LOGIN);
                String firstName = set.getString(USER_FIRST_NAME);

                user.setId(userId);
                user.setEmail(email);
                user.setFirstName(firstName);

                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }

    @Override
    public void setUserAvailable(User user, boolean available) throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(SET_USER_AVAILABLE_QUERY);
            statement.setBoolean(1, available);
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during changing user state");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            dbConnector.closeConnection(connection);
        }
    }
}
