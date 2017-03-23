package com.training.web_store.command.impl.user;

import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;

public class SignOutCommand extends UserCommand {
    private static final String START_PAGE = "/welcome";

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            service.signOut(session);
            if (session != null) {
                log.info("Success completed log out");
            }

            Redirector.redirect(response, START_PAGE);
        } catch (ServiceException e) {
            log.log(Level.SEVERE, "Cannot sign out", e);
        }
    }
}
