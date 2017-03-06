package com.training.web_store.command.impl.user;

import com.training.web_store.bean.account.User;
import com.training.web_store.command.Command;
import com.training.web_store.service.UserService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import com.training.web_store.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInCommand implements Command {
    private static final Logger log = Logger.getLogger(SignUpCommand.class.getName());

    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String ERROR_PARAMETER = "error-message";

    private static final String USER_INFO = "user";
    private static final String LOCALE_INFO = "locale";

    private static final String JSP_IM = "/im";
    private static final String JSP_SIGN_IN_ERROR = "/error?value=sign_in";

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        try {
            User user = service.signIn(email, password);

            HttpSession session = request.getSession(true);
            if (user != null) {
                Locale locale = new Locale(user.getLocale());

                session.setAttribute(LOCALE_INFO, locale);
                session.setAttribute(USER_INFO, user);

                Redirector.redirect(response, JSP_IM);
            } else {
                session.setAttribute(ERROR_PARAMETER, "User not found. Please register at first");
                Redirector.redirect(response, JSP_SIGN_IN_ERROR);
            }
        } catch (ServiceException e) {
            String error = "Error while performing sign in";
            log.log(Level.WARNING, error, e);
            Redirector.redirect(response, JSP_SIGN_IN_ERROR);
        }
    }
}
