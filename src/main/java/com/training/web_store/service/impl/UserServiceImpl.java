package com.training.web_store.service.impl;

import com.training.web_store.bean.account.User;
import com.training.web_store.dao.UserDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.dao.factory.DAOFactory;
import com.training.web_store.service.UserService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.util.ArgumentParserUtil;
import com.training.web_store.util.exception.StorageException;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final String USER_INFO = "user";

    private final DAOFactory factory = DAOFactory.getInstance();
    private final UserDAO userDAO = factory.getUserDAO();
    private static final String INVALID_ARGUMENT = "Invalid arguments";

    @Override
    public void registration(User user) throws ServiceException {
        if (!ArgumentParserUtil.isValidUser(user)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            userDAO.addUser(user);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    public User signIn(String login, String password) throws ServiceException {
        if (!ArgumentParserUtil.areValidArguments(login, password)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        User user = null;
        try {
            user = userDAO.getUser(login, password);
        } catch (DAOException | StorageException e) {
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
        if (!ArgumentParserUtil.isValidArgument(userId)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        if (!ArgumentParserUtil.isValidUser(user)) {
            throw new ServiceException(INVALID_ARGUMENT);
        }
        try {
            userDAO.updateUser(userId, user);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAccounts() throws ServiceException {
        try {
            return userDAO.getUsers();
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeAccountRole(int accountId, int roleId) throws ServiceException {
        try {
            userDAO.changeRole(accountId, roleId);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void blockUser(int accountId) throws ServiceException {
        try {
            userDAO.setUserAvailable(accountId, false);
        } catch (DAOException | StorageException e) {
            throw new ServiceException(e);
        }
    }
}
