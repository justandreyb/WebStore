package com.training.web_store.command.impl.store.review;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Review;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetReviewsCommand extends StoreCommand {
    private static final String ID_PARAMETER = "thingId";
    private static final String ERROR_MESSAGE = "Something went wrong while getting product";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);
            List<Review> reviews = service.getReviews();
            String reviewsJSON = AnswerCreator.createJSONFromReviews(reviews);

            ResponseWriter.writeData(response, reviewsJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
