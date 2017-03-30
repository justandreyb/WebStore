package com.training.pages_creator.command.impl.store.product;

import com.training.pages_creator.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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


    }

    private PrintWriter getWriter(HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            //log
        }
        return writer;
    }

    private void sendForm(HttpServletResponse response, String formName) {
        /*PrintWriter writer = getWriter(response);
        if (writer != null) {
            try {
                String form = PagesKeeper.getHiddenForm(formName);
                form = AnswerCreator.create(form);
                writer.write(form);
            } catch (ProjectUtilException e) {
                String errorMessage = "Something went wrong while trying to open form";
                errorMessage = AnswerCreator.createError(errorMessage);
                writer.write(errorMessage);
            }
        } else {
            //log
        }*/
    }

}
