package com.training.web_store.command.impl.interaction;

import com.training.util.ResponseWriter;
import com.training.web_store.bean.account.User;
import com.training.web_store.command.impl.InteractionCommand;
import com.training.web_store.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetRatingCommand extends InteractionCommand {
    private static final String USER = "user";
    private static final String THING = "thingId";
    private static final String VALUE = "value";

    private static final String ERROR_USER_NOT_FOUND = "Sign in or register at first..";
    private static final String ERROR_WHILE_SETTING_RATING = "Error while setting rating";
    private static final String SUCCESS_MESSAGE = "Completed successful";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String thingIdParam = request.getParameter(THING);
        String valueParam = request.getParameter(VALUE);

        try {
            int thingId = Integer.parseInt(thingIdParam);
            byte value = Byte.parseByte(valueParam);

            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute(USER);
            if (user != null) {
                service.setRating(user.getId(), thingId, value);
                ResponseWriter.writeSuccess(response, SUCCESS_MESSAGE);
            } else {
                ResponseWriter.writeError(response, ERROR_USER_NOT_FOUND);
            }
        } catch (ServiceException e) {
            ResponseWriter.writeError(response, ERROR_WHILE_SETTING_RATING);
            log.debug(ERROR_WHILE_SETTING_RATING, e);
        }
    }
}
