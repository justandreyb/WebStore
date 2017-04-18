package com.training.web_store.command.impl.store.thing;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditThingCommand extends StoreCommand {
    private static final String ID_PARAMETER = "id";
    private static final String NAME_PARAMETER = "name";
    private static final String CATEGORY_PARAMETER = "category";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String CREATION_DATE_PARAMETER = "creationDate";
    private static final String BRAND_PARAMETER = "brand";
    private static final String ERROR_MESSAGE = "Something went wrong while adding thing";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);
        String name = request.getParameter(NAME_PARAMETER);
        String categoryParam = request.getParameter(CATEGORY_PARAMETER);
        String description = request.getParameter(DESCRIPTION_PARAMETER);
        String creationDateParam = request.getParameter(CREATION_DATE_PARAMETER);
        String brandParam = request.getParameter(BRAND_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            Date creationDate = dateFormat.parse(creationDateParam);
            int category = Integer.parseInt(categoryParam);
            int brand = Integer.parseInt(brandParam);

            service.updateThing(id, name, description, creationDate, category, brand);

            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException | ParseException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
