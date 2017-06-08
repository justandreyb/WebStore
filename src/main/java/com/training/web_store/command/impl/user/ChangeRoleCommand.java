package com.training.web_store.command.impl.user;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.UserCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleCommand extends UserCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting accounts";

    private static final String ROLE_ID = "roleId";
    private static final String ACCOUNT_ID = "accountId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String roleIdParam = request.getParameter(ROLE_ID);
            String accountIdParam = request.getParameter(ACCOUNT_ID);

            int roleId = Integer.parseInt(roleIdParam);
            int accountId = Integer.parseInt(accountIdParam);

            service.changeAccountRole(accountId, roleId);

            ResponseWriter.writeSuccess(response, "Complete");
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
