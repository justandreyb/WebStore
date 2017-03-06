package com.training.web_store.controller.impl.command.user;

import com.training.web_store.controller.Command;
import com.training.web_store.service.UserService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import com.training.web_store.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignOutCommand implements Command {
    private static final Logger log = Logger.getLogger(SignOutCommand.class.getName());

    private static final String START_PAGE = "/welcome";

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService service = serviceFactory.getUserService();

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
