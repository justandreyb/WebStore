package com.training.pages_creator.command.impl;

import com.training.pages_creator.command.Command;
import com.training.pages_creator.service.ViewService;
import com.training.pages_creator.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseCommand implements Command {
    private ServiceFactory factory = ServiceFactory.getInstance();
    ViewService viewService = factory.getViewService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String entity) { }
}
