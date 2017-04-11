package com.training.web_store.service;

import com.training.web_store.bean.account.User;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpSession;

public interface UserService {
    void registration(User user) throws ServiceException;

    User signIn(String login, String password) throws ServiceException;

    void signOut(HttpSession session) throws ServiceException;

    void updateAccountInfo(int userId, User user) throws ServiceException;
}
