package com.training.web_store.command.impl.user;

import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.command.impl.UserCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class UpdateAccountInfoCommand extends UserCommand {
    private static final String ID_PARAMETER = "id";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String FIRST_NAME_PARAMETER = "firstName";
    private static final String LAST_NAME_PARAMETER = "lastName";
    private static final String PHONE_PARAMETER = "phone";
    private static final String GENDER_PARAMETER = "gender";
    private static final String ADDRESS_PARAMETER = "address";
    private static final String LOCALE_PARAMETER = "language";

    private static final String ERROR_WITH_UPDATING_ACCOUNT = "Error while perform update account";

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
            String userIdParam = request.getParameter(ID_PARAMETER);
            int userId = Integer.parseInt(userIdParam);

            User user = new User(email, password, firstName, lastName, phone,
                    gender, address, locale);

            service.updateAccountInfo(userId, user);
            HttpSession session = request.getSession(true);
            service.signOut(session);

            user = service.signIn(email, password);

            Locale currentLocale = new Locale(locale);

            session.setAttribute(LOCALE_PARAMETER, currentLocale);
            session.setAttribute(user.getRole(), user);
            ResponseWriter.writeSuccess(response, "Complete");

        } catch (ServiceException e) {
            log.warn(ERROR_WITH_UPDATING_ACCOUNT, e);
            ResponseWriter.writeError(response, ERROR_WITH_UPDATING_ACCOUNT);
        }
    }
}
