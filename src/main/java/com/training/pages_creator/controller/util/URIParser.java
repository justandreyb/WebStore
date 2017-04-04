package com.training.pages_creator.controller.util;

import com.training.pages_creator.bean.ParsedRequest;
import com.training.pages_creator.controller.util.exception.UtilException;

public class URIParser {

    private static final String REQUESTED_PAGE = "/entity/";
    private static final String EMPTY_STRING = "";
    private static final String SLASH = "/";
    private static final int BEGIN_REQUEST = 0;

    public static ParsedRequest parseRequest(String url) throws UtilException {
        if (url == null || url.isEmpty()) {
            throw new UtilException("Something went wrong while open form");
        }

        url = url.replace(REQUESTED_PAGE, EMPTY_STRING);

        if (!url.contains(SLASH)) {
            throw new UtilException("This form cannot be opened");
        }

        int delimiterIndex = url.indexOf(SLASH);
        String entity = url.substring(BEGIN_REQUEST, delimiterIndex);
        String command = url.substring(delimiterIndex + 1);

        return new ParsedRequest(entity, command);
    }
}
