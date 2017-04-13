package com.training.web_store.command.impl.store.thing;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Thing;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetThingsCommand extends StoreCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting product";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Thing> things = service.getThings();
            String thingsJSON = AnswerCreator.createJSONFromThings(things);

            ResponseWriter.writeData(response, thingsJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
