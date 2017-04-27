package com.training.web_store.command.impl.store.role;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.Role;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetRolesCommand extends StoreCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting brands";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Role> brands = service.getRoles();
            String rolesJSON = AnswerCreator.createJSONFromRoles(brands);

            ResponseWriter.writeData(response, rolesJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
