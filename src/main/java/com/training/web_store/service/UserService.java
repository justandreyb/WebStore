package com.training.web_store.service;

import com.training.web_store.bean.account.User;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpSession;

public interface UserService {
    void registration(String login, String password, String firstName, String lastName,
                      String phoneNumber, String gender, String address, String locale) throws ServiceException;

    User signIn(String login, String password) throws ServiceException;

    void signOut(HttpSession session) throws ServiceException;

    void updateAccountInfo(int userId, String login, String password, String firstName, String lastName,
                           String phoneNumber, String gender, String address, String locale) throws ServiceException;
}
