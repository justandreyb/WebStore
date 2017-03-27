package com.training.web_store.command.impl.store.product;

import com.training.web_store.command.impl.store.StoreCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotoCommand extends StoreCommand {
    private static final String ACTION = "action";

    private static final String ADDING_FORM = "getPhotoAddingForm";
    private static final String DELETING_FORM = "getPhotoDeletingForm";

    private static final String ADD_PHOTO = "addPhoto";
    private static final String DELETE_PHOTO = "deletePhoto";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case ADDING_FORM:
                sendAddingPhotoForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingPhotoForm(request, response);
                break;
            case ADD_PHOTO:
                handleAddPhoto(request, response);
                break;
            case DELETE_PHOTO:
                handleDeletePhoto(request, response);
                break;
            default:
                break;
        }
    }

    private void sendAddingPhotoForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendDeletingPhotoForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddPhoto(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeletePhoto(HttpServletRequest request, HttpServletResponse response) {

    }
}
