package com.training.web_store.command.impl.store.category;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCategoryCommand extends StoreCommand {
    private static final String ID_PARAMETER = "categoryId";
    private static final String NAME_PARAMETER = "name";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String ERROR_MESSAGE = "Something went wrong while editing category";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);
        String name = request.getParameter(NAME_PARAMETER);
        String description = request.getParameter(DESCRIPTION_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);

            service.updateCategory(id, name, description);

            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
