package com.training.web_store.command.impl.store.image;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddImageCommand extends StoreCommand {
    private static final String PRODUCT_ID_PARAMETER = "productId";
    private static final String THING_ID_PARAMETER = "thingId";
    private static final String HREF_PARAMETER = "href";
    private static final String REAL_NAME_PARAMETER = "realName";
    private static final String ERROR_MESSAGE = "Something went wrong while adding brand";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String productParam = request.getParameter(PRODUCT_ID_PARAMETER);
        String thingParam = request.getParameter(THING_ID_PARAMETER);
        String realName = request.getParameter(REAL_NAME_PARAMETER);

        try {
            if (productParam != null) {
                int product = Integer.parseInt(productParam);
                service.addPhotoForProduct(product, realName);
            } else if(thingParam != null) {
                int thing = Integer.parseInt(thingParam);
                service.addPhotoForThing(thing, realName);
            }
            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
