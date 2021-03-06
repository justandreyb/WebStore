package com.training.web_store.command.impl.store.brand;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBrandCommand extends StoreCommand {
    private static final String NAME_PARAMETER = "name";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String ERROR_MESSAGE = "Something went wrong while adding brand";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER);
        String description = request.getParameter(DESCRIPTION_PARAMETER);

        try {
            //TODO: Change to 1 method
            if (description != null) {
                service.addBrand(name, description);
            } else {
                service.addBrand(name);
            }
            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
