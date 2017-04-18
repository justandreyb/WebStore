package com.training.web_store.command.impl.interaction;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.InteractionCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchProductCommand extends InteractionCommand {
    private static final String REQUESTED_PRODUCT = "targetProduct";
    private static final String ERROR_WHILE_SEARCHING = "Error while getting order";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String targetProduct = request.getParameter(REQUESTED_PRODUCT);
        try {
            service.searchProduct(targetProduct);
        } catch (ServiceException e) {
            ResponseWriter.writeError(response, ERROR_WHILE_SEARCHING);
            log.debug(ERROR_WHILE_SEARCHING, e);
        }
    }
}
