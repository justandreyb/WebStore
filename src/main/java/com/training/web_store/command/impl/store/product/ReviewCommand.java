package com.training.web_store.command.impl.store.product;

import com.training.web_store.command.impl.store.StoreCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewCommand extends StoreCommand {
    private static final String ACTION = "action";

    private static final String ADDING_FORM = "getReviewAddingForm";
    private static final String DELETING_FORM = "getReviewDeletingForm";

    private static final String ADD_REVIEW = "addReview";
    private static final String DELETE_REVIEW = "deleteReview";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case ADDING_FORM:
                sendAddingReviewForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingReviewForm(request, response);
                break;
            case ADD_REVIEW:
                handleAddReview(request, response);
                break;
            case DELETE_REVIEW:
                handleDeleteReview(request, response);
                break;
            default:
                break;
        }
    }

    private void sendAddingReviewForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendDeletingReviewForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddReview(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteReview(HttpServletRequest request, HttpServletResponse response) {

    }
}
