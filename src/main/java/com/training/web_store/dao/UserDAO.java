package com.training.web_store.dao;

import com.training.web_store.bean.account.User;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {
    void addUser(User user) throws DAOException;

    void updateUser(int userId, User user) throws DAOException;

    User getUser(String login, String password) throws DAOException;

    List<User> getUsers() throws DAOException;

    void setUserAvailable(User user, boolean available) throws DAOException;
}
