package com.training.web_store.command.impl.store.product;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Product;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetProductsCommand extends StoreCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting products";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> products = service.getProducts();
            String productsJSON = AnswerCreator.createJSONFromProducts(products);

            ResponseWriter.writeData(response, productsJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
