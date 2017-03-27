package com.training.web_store.command.impl.store.product;

import com.training.web_store.command.impl.store.StoreCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryCommand extends StoreCommand {
    private static final String ACTION = "action";

    private static final String ADDING_FORM = "getCategoryAddingForm";
    private static final String EDITING_FORM = "getCategoryEditingForm";
    private static final String DELETING_FORM = "getCategoryDeletingForm";

    private static final String ADD_CATEGORY = "addCategory";
    private static final String EDIT_CATEGORY = "editCategory";
    private static final String DELETE_CATEGORY = "deleteCategory";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case ADDING_FORM:
                sendAddingCategoryForm(request, response);
                break;
            case EDITING_FORM:
                sendEditingCategoryForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingCategoryForm(request, response);
                break;
            case ADD_CATEGORY:
                handleAddCategory(request, response);
                break;
            case EDIT_CATEGORY:
                handleEditCategory(request, response);
                break;
            case DELETE_CATEGORY:
                handleDeleteCategory(request, response);
                break;
            default:
                break;
        }
    }

    private void sendAddingCategoryForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendEditingCategoryForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendDeletingCategoryForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddCategory(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleEditCategory(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response) {

    }
}
