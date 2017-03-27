package com.training.web_store.command.impl.store.product;

import com.training.web_store.command.impl.store.StoreCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BrandCommand extends StoreCommand {
    private static final String ACTION = "action";

    private static final String ADDING_FORM = "getBrandAddingForm";
    private static final String EDITING_FORM = "getBrandEditingForm";
    private static final String DELETING_FORM = "getBrandDeletingForm";

    private static final String ADD_BRAND = "addBrand";
    private static final String EDIT_BRAND = "editBrand";
    private static final String DELETE_BRAND = "deleteBrand";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case ADDING_FORM:
                sendAddingBrandForm(request, response);
                break;
            case EDITING_FORM:
                sendEditingBrandForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingBrandForm(request, response);
                break;
            case ADD_BRAND:
                handleAddBrand(request, response);
                break;
            case EDIT_BRAND:
                handleEditBrand(request, response);
                break;
            case DELETE_BRAND:
                handleDeleteBrand(request, response);
                break;
            default:
                break;
        }
    }

    private void sendAddingBrandForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendEditingBrandForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendDeletingBrandForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddBrand(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleEditBrand(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteBrand(HttpServletRequest request, HttpServletResponse response) {

    }
}
