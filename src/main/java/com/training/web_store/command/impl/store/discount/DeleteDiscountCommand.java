package com.training.web_store.command.impl.store.discount;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDiscountCommand extends StoreCommand {
    private static final String ID_PARAMETER = "id";
    private static final String ERROR_MESSAGE = "Something went wrong while deleting discount";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);

            service.deleteDiscount(id);

            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
