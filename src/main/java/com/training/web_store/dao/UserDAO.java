package com.training.web_store.dao;

import com.training.web_store.bean.account.User;
import com.training.web_store.dao.exception.DAOException;

public interface UserDAO {
    void addUser(User user) throws DAOException;

    void updateUser(int userId, User user) throws DAOException;

    User getUser(String login, String password) throws DAOException;

    void setUserAvailable(User user, boolean available) throws DAOException;
}
