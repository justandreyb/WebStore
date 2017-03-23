package com.training.web_store.command.impl.store.product.remove;

import com.training.web_store.command.impl.store.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveBrandCommand extends StoreCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            storeService.deleteBrand(0);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}