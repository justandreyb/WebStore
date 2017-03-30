package com.training.web_store.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Redirector {
    private static final Logger log = Logger.getLogger(Redirector.class);

    private static final String JSP_PATH = "/WEB-INF/jsp";

    public static void forward(HttpServletRequest request, HttpServletResponse response, String jspPath) {
        try {
            jspPath = getRealPath(jspPath);
            ServletContext context = request.getServletContext().getContext(jspPath);
            context.getRequestDispatcher(jspPath).forward(request, response);
        } catch (ServletException | IOException e) {
            log.warn("Error while forwarding", e);
        }
    }

    public static void redirect(HttpServletResponse response, String jspPath) {
        try {
            response.sendRedirect(jspPath);
        } catch (IOException e) {
            log.warn("Error while redirecting", e);
        }
    }

    private static String getRealPath(String jspName) {
        StringBuilder builder = new StringBuilder();
        builder.append(JSP_PATH);
        builder.append(jspName);
        if (!isRequest(jspName)) {
            builder.append(".jsp");
        }

        return builder.toString();
    }

    private static boolean isRequest(String uri) {
        return uri.contains("?");
    }
}
