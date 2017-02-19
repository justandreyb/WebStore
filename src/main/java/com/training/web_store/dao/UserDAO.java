package com.training.web_store.dao;

import com.training.web_store.bean.User;
import com.training.web_store.dao.exception.DAOException;

public interface UserDAO {
    void addUser(String login, String password, String firstName, String lastName, String gender,
                 String address, String phoneNumber, String locale) throws DAOException;
    User getUser(String login, String password) throws DAOException;
}
