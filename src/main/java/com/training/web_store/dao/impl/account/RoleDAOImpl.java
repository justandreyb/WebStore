package com.training.web_store.dao.impl.account;

import com.training.web_store.bean.account.Role;
import com.training.web_store.dao.RoleDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.database.DBConnector;
import com.training.web_store.util.exception.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final DBConnector dbConnector = DBConnector.getInstance();

    private static final String DATABASE = "web_store";
    private static final String ROLE_TABLE = "role";
    private static final String ROLE_ID = "id";
    private static final String ROLE_VALUE = "value";

    private static final String HIDDEN_ROLE_ID = "2";

    private static final String GET_ROLES_QUERY =
            "SELECT " +
                ROLE_ID + ", " +
                ROLE_VALUE +
            " FROM " + DATABASE + "." + ROLE_TABLE +
            " WHERE " +
                ROLE_ID + "!=" + HIDDEN_ROLE_ID;

    @Override
    public List<Role> getRoles() throws DAOException, StorageException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Role> roles = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_ROLES_QUERY);

            set = statement.executeQuery();

            roles = new ArrayList<Role>();

            while (set.next()) {
                Role role = new Role();

                int roleId = set.getInt(ROLE_ID);
                String value = set.getString(ROLE_VALUE);

                role.setId(roleId);
                role.setValue(value);

                roles.add(role);
            }
            return roles;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            dbConnector.closeConnection(connection, statement, set);
        }
    }
}
