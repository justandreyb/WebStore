package com.training.web_store.command.impl.user;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.UserCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutCommand extends UserCommand {

    private static final String ERROR_WITH_SIGN_OUT = "Cannot sign out";

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            service.signOut(session);
            ResponseWriter.writeSuccess(response, "Complete");
        } catch (ServiceException e) {
            log.debug(ERROR_WITH_SIGN_OUT, e);
            ResponseWriter.writeError(response, ERROR_WITH_SIGN_OUT);
        }
    }
}
