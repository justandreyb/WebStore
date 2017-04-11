package com.training.web_store.service.impl;

import com.training.web_store.bean.account.User;
import com.training.web_store.dao.UserDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.dao.factory.DAOFactory;
import com.training.web_store.service.UserService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.util.ArgumentParserUtil;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    private static final String USER_INFO = "user";

    private final DAOFactory factory = DAOFactory.getInstance();

    @Override
    public void registration(User user) throws ServiceException {
        if (!ArgumentParserUtil.isValidUser(user)) {
            throw new ServiceException("Invalid arguments");
        }

        UserDAO userDAO = factory.getUserDAO();
        try {
            userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User signIn(String login, String password) throws ServiceException {
        if (!ArgumentParserUtil.isValidArguments(login, password)) {
            throw new ServiceException("Invalid arguments");
        }
        UserDAO userDAO = factory.getUserDAO();
        User user = null;
        try {
            user = userDAO.getUser(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    public void signOut(HttpSession session) throws ServiceException {
        if (session == null) {
            throw new ServiceException("Session not found");
        }

        session.removeAttribute(USER_INFO);

        if (session.getAttributeNames().hasMoreElements()) {
            session.invalidate();
        }
    }

    @Override
    public void updateAccountInfo(int userId, User user) throws ServiceException {
        if (!ArgumentParserUtil.isValidUser(user)) {
            throw new ServiceException("Invalid arguments");
        }

        UserDAO userDAO = factory.getUserDAO();
        try {
            userDAO.updateUser(userId, user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
