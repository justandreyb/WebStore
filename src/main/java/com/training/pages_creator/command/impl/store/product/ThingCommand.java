package com.training.pages_creator.command.impl.store.product;

import com.training.pages_creator.command.impl.store.StoreCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThingCommand extends StoreCommand {
    private static final String ACTION = "action";

    private static final String ADDING_FORM = "getThingAddingForm";
    private static final String EDITING_FORM = "getThingEditingForm";
    private static final String DELETING_FORM = "getThingDeletingForm";

    private static final String ADD_THING = "addThing";
    private static final String EDIT_THING = "editThing";
    private static final String DELETE_THING = "deleteThing";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case ADDING_FORM:
                sendAddingThingForm(request, response);
                break;
            case EDITING_FORM:
                sendEditingThingForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingThingForm(request, response);
                break;
            case ADD_THING:
                handleAddThing(request, response);
                break;
            case EDIT_THING:
                handleEditThing(request, response);
                break;
            case DELETE_THING:
                handleDeleteThing(request, response);
                break;
            default:
                break;
        }
    }

    private void sendAddingThingForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendEditingThingForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void sendDeletingThingForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleAddThing(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleEditThing(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteThing(HttpServletRequest request, HttpServletResponse response) {

    }
}
