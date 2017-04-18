package com.training.web_store.command.impl.store.review;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddReviewCommand extends StoreCommand {
    private static final String ID_PARAMETER = "thingId";
    private static final String TEXT_PARAMETER = "text";
    private static final String ERROR_MESSAGE = "Something went wrong while adding review";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);
        String text = request.getParameter(TEXT_PARAMETER);

        try {
            int thingId = Integer.parseInt(idParam);

            service.addReview(thingId, text);

            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
