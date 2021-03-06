package com.training.web_store.command.impl.store.product;

import com.training.util.Redirector;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Product;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductCommand extends StoreCommand {

    private static final String ID_PARAMETER = "id";
    private static final String ERROR_MESSAGE = "Something went wrong while getting product";
    private static final String NOTHING_TO_SHOW = "Here is nothing to show";

    private static final String PRODUCT_ATTRIBUTE = "product";
    private static final String PRODUCT_PAGE = "/product";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);
            Product product = service.getProduct(id);

            if (product != null) {
                request.setAttribute(PRODUCT_ATTRIBUTE, product);
                Redirector.forward(request, response, PRODUCT_PAGE);
            } else {
                ResponseWriter.writeError(response, NOTHING_TO_SHOW);
            }
        } catch (ServiceException e) {
            log.debug(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }

}
