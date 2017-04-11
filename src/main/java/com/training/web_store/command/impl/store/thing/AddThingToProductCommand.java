package com.training.web_store.command.impl.store.thing;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddThingToProductCommand extends StoreCommand {
    private static final String PRODUCT_PARAMETER = "product";
    private static final String THING_PARAMETER = "thing";
    private static final String ERROR_MESSAGE = "Something went wrong while adding thing to product";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String productParam = request.getParameter(PRODUCT_PARAMETER);
        String thingParam = request.getParameter(THING_PARAMETER);

        try {
            int product = Integer.parseInt(productParam);
            int thing = Integer.parseInt(thingParam);

            service.addThingToProduct(product, thing);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
