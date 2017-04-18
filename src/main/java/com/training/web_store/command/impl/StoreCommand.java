package com.training.web_store.command.impl;

import com.training.web_store.command.Command;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreCommand implements Command {
    private static final ServiceFactory factory = ServiceFactory.getInstance();
    protected static final StoreService service = factory.getStoreService();
    protected static final Logger log = Logger.getLogger(StoreCommand.class.getName());
    protected static final String SUCCESS_MESSAGE = "Completed successful";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) { }
}
