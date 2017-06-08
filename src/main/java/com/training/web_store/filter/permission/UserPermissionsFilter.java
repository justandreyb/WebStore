package com.training.web_store.filter.permission;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("Duplicates")
@WebFilter(filterName = "UserPermissionsFilter")
public class UserPermissionsFilter implements Filter {
    private static final String USER_ATTRIBUTE = "user";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest;
        HttpServletResponse httpResponse;
        httpResponse = (HttpServletResponse) resp;
        httpRequest = (HttpServletRequest) req;

        HttpSession httpSession;
        httpSession = httpRequest.getSession(false);

        boolean isUser = false;

        if (httpSession != null) {
            if (httpSession.getAttribute(USER_ATTRIBUTE) != null) {
                isUser = true;
            }
        }

        if (!isUser) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
