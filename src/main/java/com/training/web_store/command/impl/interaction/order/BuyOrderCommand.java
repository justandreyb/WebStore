package com.training.web_store.command.impl.interaction.order;

import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.bean.store.Order;
import com.training.web_store.command.impl.InteractionCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyOrderCommand extends InteractionCommand {
    private static final String USER = "user";
    private static final String ERROR_USER_NOT_FOUND = "Sign in or register at first..";
    private static final String ERROR_WHILE_BUYING_ORDER = "Error while buying order";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute(USER);
            if (user != null) {
                Order order = service.getOrder(user.getId());
                if (order == null) {
                    order = service.createOrder(user.getId());
                }
                service.buyOrder(order.getId());
            } else {
                ResponseWriter.writeError(response, ERROR_USER_NOT_FOUND);
            }
        } catch (ServiceException e) {
            ResponseWriter.writeError(response, ERROR_WHILE_BUYING_ORDER);
            log.debug(ERROR_WHILE_BUYING_ORDER, e);
        }
    }
}
