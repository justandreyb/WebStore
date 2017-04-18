package com.training.web_store.command.impl.interaction.order;

import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.bean.store.Order;
import com.training.web_store.command.impl.InteractionCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetOrderCommand extends InteractionCommand {
    private static final String USER = "user";
    private static final String ERROR_USER_NOT_FOUND = "Sign in or register at first..";
    private static final String ERROR_WHILE_GETTING_ORDER = "Error while getting order";

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
                String orderJSON = AnswerCreator.createJSONFromOrder(order);
                ResponseWriter.writeData(response, orderJSON);
            } else {
                ResponseWriter.writeError(response, ERROR_USER_NOT_FOUND);
            }
        } catch (ServiceException e) {
            ResponseWriter.writeError(response, ERROR_WHILE_GETTING_ORDER);
            log.debug(ERROR_WHILE_GETTING_ORDER, e);
        }
    }
}
