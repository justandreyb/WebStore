package com.training.web_store.command.impl.user;

import com.training.web_store.command.Command;
import com.training.web_store.service.UserService;
import com.training.web_store.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class UserCommand implements Command {
    private static final ServiceFactory factory = ServiceFactory.getInstance();
    protected static final UserService service = factory.getUserService();
    protected static final Logger log = Logger.getLogger(UserCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) { }
}
