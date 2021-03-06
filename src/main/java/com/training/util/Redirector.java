package com.training.util;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirector {
    private static final Logger log = Logger.getLogger(Redirector.class);

    private static final String JSP_PATH = "/WEB-INF/jsp";

    public static void forward(HttpServletRequest request, HttpServletResponse response, String root, String jspName) {
        try {
            jspName = getRealPath(root, jspName);
            ServletContext context = request.getServletContext().getContext(jspName);
            context.getRequestDispatcher(jspName).forward(request, response);
        } catch (ServletException | IOException e) {
            log.warn("Error while forwarding", e);
        }
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String jspName) {
        forward(request, response, JSP_PATH, jspName);
    }

    private static String getRealPath(String root, String jspName) {
        StringBuilder builder = new StringBuilder();
        builder.append(root);
        builder.append(jspName);
        if (!isRequest(jspName)) {
            builder.append(".jsp");
        }

        return builder.toString();
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            response.sendRedirect(path);
            return;
        } catch (IOException e) {
            log.warn("Error while forwarding");
        }
    }

    private static boolean isRequest(String uri) {
        return uri.contains("?");
    }
}
