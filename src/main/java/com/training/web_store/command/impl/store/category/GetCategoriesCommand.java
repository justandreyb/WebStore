package com.training.web_store.command.impl.store.category;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Category;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCategoriesCommand extends StoreCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting category";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Category> categories = service.getCategories();
            String categoriesJSON = AnswerCreator.createJSONFromCategories(categories);

            ResponseWriter.writeData(response, categoriesJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
