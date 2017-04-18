package com.training.web_store.command.impl.store.thing;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteThingFromProductCommand extends StoreCommand {
    private static final String PRODUCT_PARAMETER = "product";
    private static final String THING_PARAMETER = "thing";
    private static final String ERROR_MESSAGE = "Something went wrong while adding review";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String productIdParam = request.getParameter(PRODUCT_PARAMETER);
        String thingIdParam = request.getParameter(THING_PARAMETER);

        try {
            int thingId = Integer.parseInt(thingIdParam);
            int productId = Integer.parseInt(productIdParam);

            service.deleteThingFromProduct(productId, thingId);

            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
