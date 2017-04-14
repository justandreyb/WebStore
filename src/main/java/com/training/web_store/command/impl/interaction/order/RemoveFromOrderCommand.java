package com.training.web_store.command.impl.interaction.order;

import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.bean.store.Order;
import com.training.web_store.command.impl.InteractionCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveFromOrderCommand extends InteractionCommand {
    private static final String PRODUCT_ID = "productId";
    private static final String USER = "user";
    private static final String ERROR_USER_NOT_FOUND = "Sign in or register at first..";
    private static final String ERROR_WHILE_ADDING_TO_ORDER = "Error while adding to order";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String productIdParam = request.getParameter(PRODUCT_ID);

        try {
            int productId = Integer.parseInt(productIdParam);
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute(USER);
            if (user != null) {
                Order order = service.getOrder(user.getId());
                if (order == null) {
                    order = service.createOrder(user.getId());
                }
                service.removeFromOrder(order.getId(), productId);
            } else {
                ResponseWriter.writeError(response, ERROR_USER_NOT_FOUND);
            }
        } catch (ServiceException e) {
            ResponseWriter.writeError(response, ERROR_WHILE_ADDING_TO_ORDER);
            log.debug(ERROR_WHILE_ADDING_TO_ORDER, e);
        }
    }
}
