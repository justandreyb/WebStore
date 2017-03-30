package com.training.web_store.command.impl.store;

import com.training.web_store.command.Command;
import com.training.web_store.service.StoreService;
import com.training.web_store.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class StoreCommand implements Command {
    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    protected static final StoreService storeService = serviceFactory.getStoreService();
    protected static final Logger log = Logger.getLogger(StoreCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) { }
}
