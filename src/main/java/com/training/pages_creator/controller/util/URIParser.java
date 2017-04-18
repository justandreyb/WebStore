package com.training.pages_creator.controller.util;

import com.training.pages_creator.bean.ParsedRequest;
import com.training.pages_creator.controller.util.exception.UtilException;

import javax.servlet.http.HttpServletRequest;

public class URIParser {

    private static final String REQUESTED_PAGE = "/entity/";
    private static final String EMPTY_STRING = "";
    private static final String COMMAND = "command";
    private static final String ACTION = "action";
    private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong while open form";
    private static final int BEGIN_REQUEST = 0;

    public static ParsedRequest parseRequest(HttpServletRequest request) throws UtilException {
        String url = request.getRequestURI();

        if (url == null || url.isEmpty()) {
            throw new UtilException(DEFAULT_ERROR_MESSAGE);
        }

        url = url.replace(REQUESTED_PAGE, EMPTY_STRING);
        String entity = url;
        String command = request.getParameter(COMMAND);
        String action = request.getParameter(ACTION);

        if (!isCorrectParams(entity, command, action)) {
            throw new UtilException(DEFAULT_ERROR_MESSAGE);
        }

        return new ParsedRequest(entity, action, command);
    }

    private static boolean isCorrectParams(String ... elements) {
        boolean result = true;
        for (String element : elements) {
            if (element == null || element.isEmpty()) {
                result = false;
            }
        }

        return result;
    }
}
