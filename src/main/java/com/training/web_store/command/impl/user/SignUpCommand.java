package com.training.web_store.command.impl.user;

import com.training.web_store.bean.account.User;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class SignUpCommand extends UserCommand {
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String FIRST_NAME_PARAMETER = "first_name";
    private static final String LAST_NAME_PARAMETER = "last_name";
    private static final String PHONE_PARAMETER = "phone_number";
    private static final String GENDER_PARAMETER = "gender";
    private static final String ADDRESS_PARAMETER = "address";
    private static final String LOCALE_PARAMETER = "locale";

    private static final String ERROR_PARAMETER = "error-message";

    private static final String USER_INFO = "user";

    private static final String JSP_IM = "/im";
    private static final String JSP_REGISTRATION_ERROR = "/error?value=register";


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
            service.registration(email, password, firstName, lastName,
                    phone, gender, address, locale);
            User user = service.signIn(email, password);
            HttpSession session = request.getSession(true);
            if (user != null) {
                Locale currentLocale = new Locale(locale);

                session.setAttribute(LOCALE_PARAMETER, currentLocale);
                session.setAttribute(USER_INFO, user);
                Redirector.redirect(response, JSP_IM);
            } else {
                session.setAttribute(ERROR_PARAMETER, "User not found");
                Redirector.redirect(response, JSP_REGISTRATION_ERROR);
            }
        } catch (ServiceException e) {
            String error = "Error while perform registration";
            log.warn(error, e);
            Redirector.redirect(response, JSP_REGISTRATION_ERROR);
        }
    }
}
