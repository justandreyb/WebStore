package com.training.web_store.command.impl.store.product;

import com.training.web_store.command.impl.store.StoreCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductCommand extends StoreCommand {
    private static final String ACTION = "action";

    private static final String ADDING_FORM = "getProductAddingForm";
    private static final String EDITING_FORM = "getProductEditingForm";
    private static final String DELETING_FORM = "getProductDeletingForm";
    private static final String THING_ADDING_FORM = "getThingAddingToProductForm";
    private static final String THING_DELETING_FORM = "getThingDeletingFromProductForm";

    private static final String ADD_PRODUCT = "addProduct";
    private static final String EDIT_PRODUCT = "editProduct";
    private static final String DELETE_PRODUCT = "deleteProduct";
    private static final String ADD_THING_TO_PRODUCT = "addThingToProduct";
    private static final String DELETE_THING_FROM_PRODUCT = "deleteThingFromProduct";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case ADDING_FORM:
                sendAddingProductForm(request, response);
                break;
            case EDITING_FORM:
                sendEditingProductForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingProductForm(request, response);
                break;
            case THING_ADDING_FORM:
                sendThingAddingToProductForm(request, response);
                break;
            case THING_DELETING_FORM:
                sendThingDeletingFromProductForm(request, response);
                break;
            case ADD_PRODUCT:
                handleAddProduct(request, response);
                break;
            case EDIT_PRODUCT:
                handleEditProduct(request, response);
                break;
            case DELETE_PRODUCT:
                handleDeleteProduct(request, response);
                break;
            case ADD_THING_TO_PRODUCT:
                handleAddThingToProduct(request, response);
                break;
            case DELETE_THING_FROM_PRODUCT:
                handleDeleteThingFromProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void sendAddingProductForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendEditingProductForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendDeletingProductForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendThingAddingToProductForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendThingDeletingFromProductForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleEditProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddThingToProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteThingFromProduct(HttpServletRequest request, HttpServletResponse response) {

    }
}
