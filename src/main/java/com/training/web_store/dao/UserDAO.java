package com.training.web_store.dao;

import com.training.web_store.bean.account.User;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public interface UserDAO {
    void addUser(User user) throws DAOException, StorageException;

    void updateUser(int userId, User user) throws DAOException, StorageException;

    User getUser(String login, String password) throws DAOException, StorageException;

    List<User> getUsers() throws DAOException, StorageException;

    void setUserAvailable(User user, boolean available) throws DAOException, StorageException;
}
