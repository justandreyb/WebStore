package com.training.web_store.command.impl.store.product;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProductCommand extends StoreCommand {
    private static final String ID_PARAMETER = "id";
    private static final String NAME_PARAMETER = "name";
    private static final String PRICE_PARAMETER = "price";
    private static final String CATEGORY_PARAMETER = "category";
    private static final String DISCOUNT_PARAMETER = "discount";
    private static final String ERROR_MESSAGE = "Something went wrong while editing product";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);
        String name = request.getParameter(NAME_PARAMETER);
        String priceParam = request.getParameter(PRICE_PARAMETER);
        String categoryParam = request.getParameter(CATEGORY_PARAMETER);
        String discountParam = request.getParameter(DISCOUNT_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);
            double price = Double.parseDouble(priceParam);
            int category = Integer.parseInt(categoryParam);
            if (discountParam != null) {
                int discount = Integer.parseInt(discountParam);
                service.updateProduct(id, name, price, category, discount);
            } else {
                service.updateProduct(id, name, price, category);
            }

        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
