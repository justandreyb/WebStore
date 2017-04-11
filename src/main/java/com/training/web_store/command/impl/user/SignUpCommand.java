package com.training.web_store.command.impl.user;

import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.command.impl.UserCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class SignUpCommand extends UserCommand {
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String FIRST_NAME_PARAMETER = "firstName";
    private static final String LAST_NAME_PARAMETER = "lastName";
    private static final String PHONE_PARAMETER = "phone";
    private static final String GENDER_PARAMETER = "gender";
    private static final String ADDRESS_PARAMETER = "address";
    private static final String LOCALE_PARAMETER = "language";

    private static final String ERROR_USER_NOT_FOUND = "User not found. Please register at first";
    private static final String ERROR_WITH_SIGN_UP = "Error while perform sign up";

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String firstName = request.getParameter(FIRST_NAME_PARAMETER);
        String lastName = request.getParameter(LAST_NAME_PARAMETER);
        String phone = request.getParameter(PHONE_PARAMETER);
        String gender = request.getParameter(GENDER_PARAMETER);
        String address = request.getParameter(ADDRESS_PARAMETER);
        String locale = request.getParameter(LOCALE_PARAMETER);

        try {
            User user = new User(email, password, firstName, lastName, phone,
                    gender, address, locale);

            service.registration(user);
            user = service.signIn(email, password);
            HttpSession session = request.getSession(true);
            if (user != null) {
                Locale currentLocale = new Locale(locale);

                session.setAttribute(LOCALE_PARAMETER, currentLocale);
                session.setAttribute(user.getRole(), user);
            } else {
                ResponseWriter.writeError(response, ERROR_USER_NOT_FOUND);
            }
        } catch (ServiceException e) {
            log.warn(ERROR_WITH_SIGN_UP, e);
            ResponseWriter.writeError(response, ERROR_WITH_SIGN_UP);
        }
    }
}
