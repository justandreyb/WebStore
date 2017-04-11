package com.training.web_store.command.impl.store.review;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Review;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetReviewCommand extends StoreCommand {
    private static final String ID_PARAMETER = "thingId";
    private static final String ERROR_MESSAGE = "Something went wrong while getting product";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);
            Review review = service.getReview(id);
            String reviewJSON = AnswerCreator.createJSONFromReview(review);

            ResponseWriter.writeData(response, reviewJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
