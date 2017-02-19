package com.training.web_store.dao.factory;

import com.training.web_store.dao.UserDAO;
import com.training.web_store.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory factory = new DAOFactory();

    private final UserDAO userDAOImpl = new UserDAOImpl();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return factory;
    }

    public UserDAO getUserDAO() {
        return this.userDAOImpl;
    }
}
