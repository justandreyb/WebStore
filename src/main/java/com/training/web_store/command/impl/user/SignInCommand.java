package com.training.web_store.command.impl.user;

import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.command.impl.UserCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class SignInCommand extends UserCommand {
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOCALE_INFO = "locale";

    private static final String ERROR_USER_NOT_FOUND = "User not found. Please register at first";
    private static final String ERROR_WHILE_SIGN_IN = "Error while performing sign in";


    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        try {
            User user = service.signIn(email, password);

            HttpSession session = request.getSession(true);
            if (user != null) {
                Locale locale = new Locale(user.getLocale());

                session.setAttribute(LOCALE_INFO, locale);
                session.setAttribute(user.getRole(), user);

            } else {
                ResponseWriter.writeError(response, ERROR_USER_NOT_FOUND);
            }
        } catch (ServiceException e) {
            ResponseWriter.writeError(response, ERROR_WHILE_SIGN_IN);
            log.debug(ERROR_WHILE_SIGN_IN, e);
        }
    }
}
