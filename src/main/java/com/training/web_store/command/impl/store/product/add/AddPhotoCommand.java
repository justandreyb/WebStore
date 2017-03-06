package com.training.web_store.controller.impl.command.store.product.add;

import com.training.web_store.controller.Command;
import com.training.web_store.service.factory.ServiceFactory;
import com.training.web_store.service.store.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPhotoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PhotoService photoService = serviceFactory.getPhotoService();

    }
}
