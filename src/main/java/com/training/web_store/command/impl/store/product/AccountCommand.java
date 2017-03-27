package com.training.web_store.command.impl.store.product;

import com.training.web_store.command.Command;
import com.training.web_store.util.PagesDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountCommand implements Command {
    private static final String ACTION = "action";

    private static final String CHANGING_ROLE_FORM = "getAccountChangingRoleForm";
    private static final String BLOCKING_FORM = "getAccountBlockingForm";
    private static final String DELETING_FORM = "getAccountDeletingForm";

    private static final String CHANGE_ROLE = "changeAccountRole";
    private static final String BLOCK = "blockAccount";
    private static final String DELETE = "deleteAccount";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedAction = request.getParameter(ACTION);

        switch (requestedAction) {
            case CHANGING_ROLE_FORM:
                sendChangingAccountRoleForm(request, response);
                break;
            case BLOCKING_FORM:
                sendBlockingAccountForm(request, response);
                break;
            case DELETING_FORM:
                sendDeletingAccountForm(request, response);
                break;
            case CHANGE_ROLE:
                handleChangeAccountRole(request, response);
                break;
            case BLOCK:
                handleBlockAccount(request, response);
                break;
            case DELETE:
                handleDeleteAccount(request, response);
                break;
            default:
                break;
        }
    }

    private void sendChangingAccountRoleForm(HttpServletRequest request, HttpServletResponse response) {
        String form = PagesDescriptor.getHiddenForm(CHANGE_ROLE + "Form");
    }

    private void sendBlockingAccountForm(HttpServletRequest request, HttpServletResponse response) {
        String form = PagesDescriptor.getHiddenForm(CHANGE_ROLE + "Form");
    }

    private void sendDeletingAccountForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleChangeAccountRole(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleBlockAccount(HttpServletRequest request, HttpServletResponse response) {

    }

    private void handleDeleteAccount(HttpServletRequest request, HttpServletResponse response) {

    }

    private void packAnswer() {}

}
