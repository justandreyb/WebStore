package com.training.web_store.command.impl.store.review;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteReviewCommand extends StoreCommand {
    private static final String ID_PARAMETER = "id";
    private static final String ERROR_MESSAGE = "Something went wrong while deleting review";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);

            service.deleteReview(id);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
