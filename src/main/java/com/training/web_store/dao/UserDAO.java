package com.training.web_store.dao;

import com.training.web_store.bean.User;
import com.training.web_store.dao.exception.DAOException;

public interface UserDAO {
    void addUser(User user) throws DAOException;
    User getUser(String login, String password) throws DAOException;
}
