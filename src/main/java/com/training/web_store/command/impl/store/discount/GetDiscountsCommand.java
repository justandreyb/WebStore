package com.training.web_store.command.impl.store.discount;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Discount;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetDiscountsCommand extends StoreCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting discounts";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Discount> discounts = service.getDiscounts();
            String discountJSON = AnswerCreator.createJSONFromDiscounts(discounts);

            ResponseWriter.writeData(response, discountJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
