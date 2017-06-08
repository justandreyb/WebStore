package com.training.web_store.command.impl.search;

import com.training.util.Redirector;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.store.Product;
import com.training.web_store.command.Command;
import com.training.web_store.command.impl.InteractionCommand;
import com.training.web_store.service.SearchService;
import com.training.web_store.service.exception.ServiceException;
import com.training.web_store.service.factory.ServiceFactory;
import com.training.web_store.service.impl.search.factory.SearchServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchProductCommand implements Command {
    private static final ServiceFactory factory = ServiceFactory.getInstance();
    private static final SearchServiceFactory searchServiceFactory = factory.getSearchServiceFactory();
    private static final Logger log = Logger.getLogger(InteractionCommand.class.getName());

    private static final String REQUESTED_STRING = "searchString";
    private static final String REQUESTED_SEARCH_TYPE = "searchType";
    private static final String ERROR_WHILE_SEARCHING = "Error while perform search";
    private static final String NOTHING_TO_SHOW = "Nothing to show";

    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String SEARCH_PAGE = "/search";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String searchString = request.getParameter(REQUESTED_STRING);
        String searchType = request.getParameter(REQUESTED_SEARCH_TYPE);

        try {
            SearchService service = searchServiceFactory.getService(searchType);

            List<Product> products = service.getProducts(searchString);
            /*
            String productsJSON = AnswerCreator.createJSONFromProducts(products);

            ResponseWriter.writeData(response, productsJSON);
            */
            if (products != null) {
                request.setAttribute(PRODUCTS_ATTRIBUTE, products);
                Redirector.forward(request, response, SEARCH_PAGE);
            } else {
                ResponseWriter.writeError(response, NOTHING_TO_SHOW);
                log.debug(NOTHING_TO_SHOW);
            }
        } catch (ServiceException e) {
            ResponseWriter.writeError(response, ERROR_WHILE_SEARCHING);
            log.debug(ERROR_WHILE_SEARCHING, e);
        }
    }
}
