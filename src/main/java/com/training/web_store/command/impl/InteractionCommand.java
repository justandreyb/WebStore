package com.training.web_store.command.impl;

import com.training.web_store.command.Command;
import com.training.web_store.service.InteractionService;
import com.training.web_store.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InteractionCommand implements Command {
    private static final ServiceFactory factory = ServiceFactory.getInstance();
    protected static final InteractionService service = factory.getInteractionService();
    protected static final Logger log = Logger.getLogger(InteractionCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) { }
}
