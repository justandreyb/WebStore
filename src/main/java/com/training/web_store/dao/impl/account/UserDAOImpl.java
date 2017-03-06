package com.training.web_store.dao.impl.account;

import com.training.web_store.bean.account.User;
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
    private static final String DATABASE = "web_store";
    private static final String USER_TABLE = "user";
    private static final String USER_INFO_TABLE = "customer_info";
    private static final String USER_ID = "id";
    private static final String USER_INFO_ID = "user_id";
    private static final String USER_LOGIN = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ROLE = "role_id";
    private static final String USER_IS_ACTIVE = "is_active";
    private static final String USER_LOCALE = "locale";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_PHONE = "phone_number";
    private static final String USER_GENDER = "gender";
    private static final String USER_ADDRESS = "address";

    private static final int USER_ACTIVE_STATE = 1;

    private static final String ADD_USER_QUERY =
            "INSERT INTO " + USER_TABLE + " (" +
                USER_LOGIN + ", " + USER_PASSWORD + ", " +
                USER_ROLE + ", " + USER_LOCALE +
            ") " +
            "VALUES (?, ?, ?, ?)";

    private static final String ADD_USER_INFO_QUERY =
            "INSERT INTO " + USER_INFO_TABLE + " (" +
                USER_FIRST_NAME + ", " + USER_LAST_NAME + ", " +
                USER_PHONE + ", " + USER_ADDRESS + ", " +
                USER_GENDER + ", " + USER_INFO_ID +
            ") " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_QUERY =
            "SELECT " +
                USER_INFO_TABLE + "." + USER_FIRST_NAME + ", " +
                USER_INFO_TABLE + "." + USER_LAST_NAME + ", " +
                USER_INFO_TABLE + "." + USER_GENDER + ", " +
                USER_INFO_TABLE + "." + USER_PHONE + ", " +
                USER_INFO_TABLE + "." + USER_ADDRESS + ", " +
                USER_TABLE + "." + USER_LOCALE +
            " FROM " + DATABASE + "." + USER_INFO_TABLE +
            " INNER JOIN " + DATABASE + "." + USER_TABLE +
                " ON " +
                    USER_INFO_TABLE + "." + USER_INFO_ID + "=" + USER_TABLE + "." + USER_ID +
                    " WHERE (" +
                        USER_TABLE + "." + USER_LOGIN + "=? AND " +
                        USER_TABLE + "." + USER_PASSWORD + "=? AND " +
                        USER_TABLE + "." + USER_IS_ACTIVE + "=" + USER_ACTIVE_STATE +
                    ")";

    private static final String GET_USER_ID_QUERY =
            "SELECT " +
                USER_ID +
            " FROM " + DATABASE + "." + USER_TABLE +
            " WHERE (" +
                USER_LOGIN + "=? AND " +
                USER_PASSWORD + "=?" +
            ")";

    private static final String SET_USER_AVAILABLE_QUERY =
            "UPDATE " + DATABASE + "." + USER_TABLE +
            " SET " + USER_IS_ACTIVE + "=?" +
            " WHERE " +
                USER_LOGIN + "=? AND" +
                USER_PASSWORD + "=?";

    private static final String UPDATE_USER_QUERY =
            "UPDATE " + DATABASE + "." + USER_TABLE +
                    " SET " + USER_IS_ACTIVE + "=?" +
                    " WHERE " +
                    USER_LOGIN + "=? AND" +
                    USER_PASSWORD + "=?";

    private static final int ROLE_ID_FOR_USER = 2;

    @Override
    public void addUser(User user) throws DAOException {
        Connection connection = null;
        try {
            connection = DBConnector.getConnection();

            connection.setAutoCommit(false);
            addUserAccount(connection, user);
            int userId = getUserId(connection, user);
            addUserInfo(connection, user, userId);

            connection.commit();
            connection.setAutoCommit(true);

        } catch (UtilException | SQLException e) {
            throw new DAOException("Cannot get connection to DB", e);
        } finally {
        try {
            DBConnector.closeConnection(connection);
        } catch (UtilException e) {
            throw new DAOException(e);
        }
    }
    }

    @Override
    public void updateUser(int userId, User user) throws DAOException {
        //TODO: Write
    }

    @Override
    public User getUser(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(GET_USER_QUERY);

            statement.setString(1, login);
            statement.setString(2, password);

            set = statement.executeQuery();

            User user = null;

            while (set.next()) {
                String first_name = set.getString(USER_FIRST_NAME);
                String last_name = set.getString(USER_LAST_NAME);
                String gender = set.getString(USER_GENDER);
                String phone = set.getString(USER_PHONE);
                String address = set.getString(USER_ADDRESS);
                String locale = set.getString(USER_LOCALE);

                if (ArgumentParserUtil.isValidArguments(first_name, last_name, gender, address, phone, locale)) {
                    user = new User(login, password, first_name, last_name, gender, address, phone, locale);
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

    @Override
    public void setUserAvailable(User user, boolean available) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(SET_USER_AVAILABLE_QUERY);
            statement.setBoolean(1, available);
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during changing user state");
            }

        } catch (SQLException | UtilException e) {
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

    private void addUserAccount(Connection connection, User user) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setInt(3, ROLE_ID_FOR_USER);
            statement.setString(4, user.getLocale());

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during adding new user");
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
        }
    }

    private void addUserInfo(Connection connection, User user, int userId) throws DAOException {
        if (userId == -1) {
            throw new DAOException("Error with adding user info. User not created");
        }

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_USER_INFO_QUERY);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getGender());
            statement.setInt(6, userId);

            if (statement.executeUpdate() < 1) {
                throw new DAOException("Error during adding new user");
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
        }
    }

    private int getUserId(Connection connection, User user) throws DAOException {
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            statement = connection.prepareStatement(GET_USER_ID_QUERY);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            set = statement.executeQuery();

            int userId = -1;
            while (set.next()) {
                userId = set.getInt(USER_ID);
            }
            return userId;

        } catch (SQLException e) {
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
        }
    }
}
