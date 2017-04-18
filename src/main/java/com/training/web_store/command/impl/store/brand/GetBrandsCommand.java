package com.training.web_store.command.impl.store.brand;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Brand;
import com.training.web_store.command.impl.StoreCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetBrandsCommand extends StoreCommand {
    private static final String ERROR_MESSAGE = "Something went wrong while getting brands";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Brand> brands = service.getBrands();
            String brandsJSON = AnswerCreator.createJSONFromBrands(brands);

            ResponseWriter.writeData(response, brandsJSON);
        } catch (ServiceException e) {
            log.warn(ERROR_MESSAGE, e);
            ResponseWriter.writeError(response, ERROR_MESSAGE);
        }
    }
}
