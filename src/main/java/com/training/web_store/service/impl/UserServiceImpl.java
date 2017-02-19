package com.training.web_store.service.impl;

import com.training.web_store.bean.User;
import com.training.web_store.dao.UserDAO;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.dao.factory.DAOFactory;
import com.training.web_store.service.UserService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.util.ArgumentEncoderUtil;
import com.training.web_store.util.ArgumentParserUtil;
import com.training.web_store.util.exception.UtilException;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    private static final String USER_INFO = "user";

    private DAOFactory factory = DAOFactory.getInstance();


    @Override
    public void registration(String login, String password, String firstName, String lastName,
                             String phoneNumber, String gender, String address, String locale) throws ServiceException {
        if (!ArgumentParserUtil.isValidArguments(login, password, firstName, lastName, locale)) {
            throw new ServiceException("Invalid arguments");
        }

        UserDAO userDAO = factory.getUserDAO();
        try {
            password = ArgumentEncoderUtil.encodePassword(password);
            userDAO.addUser(login, password, firstName, lastName, gender, address, phoneNumber, locale);
        } catch (DAOException | UtilException e) {
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
            password = ArgumentEncoderUtil.encodePassword(password);
            user = userDAO.getUser(login, password);
        } catch (DAOException | UtilException e) {
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
}
