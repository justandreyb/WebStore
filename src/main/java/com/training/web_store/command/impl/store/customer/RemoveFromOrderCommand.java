package com.training.web_store.controller.impl.command.store.customer;

import com.training.web_store.controller.Command;
import com.training.web_store.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFromOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
    }
}
