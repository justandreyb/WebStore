package com.training.web_store.command.impl.store.product;

import com.training.web_store.command.impl.store.StoreCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiscountCommand extends StoreCommand {
    private static final String ACTION = "action";

    private static final String ADDING_FORM = "getDiscountAddingForm";
    private static final String EDITING_FORM = "getDiscountEditingForm";
    private static final String DELETING_FORM = "getDiscountDeletingForm";

    private static final String ADD_DISCOUNT = "addDiscount";
    private static final String EDIT_DISCOUNT = "editDiscount";
    private static final String DELETE_DISCOUNT = "deleteDiscount";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case ADDING_FORM:
                sendAddingDiscountForm(request, response);
                break;
            case EDITING_FORM:
                sendEditingDiscountForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingDiscountForm(request, response);
                break;
            case ADD_DISCOUNT:
                handleAddDiscount(request, response);
                break;
            case EDIT_DISCOUNT:
                handleEditDiscount(request, response);
                break;
            case DELETE_DISCOUNT:
                handleDeleteDiscount(request, response);
                break;
            default:
                break;
        }
    }

    private void sendAddingDiscountForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendEditingDiscountForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendDeletingDiscountForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddDiscount(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleEditDiscount(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteDiscount(HttpServletRequest request, HttpServletResponse response) {

    }
}
