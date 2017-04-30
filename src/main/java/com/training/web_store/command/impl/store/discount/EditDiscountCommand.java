package com.training.web_store.command.impl.store.discount;

import com.training.util.ResponseWriter;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditDiscountCommand extends StoreCommand {
    private static final String ID_PARAMETER = "id";
    private static final String VALUE_PARAMETER = "value";
    private static final String START_DATE_PARAMETER = "startDate";
    private static final String FINISH_DATE_PARAMETER = "finishDate";
    private static final String ERROR_MESSAGE = "Something went wrong while editing discount";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAMETER);
        String valueParam = request.getParameter(VALUE_PARAMETER);
        String startDateParam = request.getParameter(START_DATE_PARAMETER);
        String finishDateParam = request.getParameter(FINISH_DATE_PARAMETER);

        try {
            int id = Integer.parseInt(idParam);
            byte value = Byte.parseByte(valueParam);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateParam);
            Date finishDate = dateFormat.parse(finishDateParam);

            service.updateDiscount(id, value, startDate, finishDate);

            ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
        } catch (ServiceException | ParseException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
