package com.training.web_store.command.impl.store.category;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Category;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCategoryCommand extends StoreCommand {
    private static final String ID_PARAMETER = "id";
    private static final String ERROR_MESSAGE = "Something went wrong while getting categories";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);
            Category category = service.getCategory(id);
            String categoryJSON = AnswerCreator.createJSONFromCategory(category);

            ResponseWriter.writeData(response, categoryJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
