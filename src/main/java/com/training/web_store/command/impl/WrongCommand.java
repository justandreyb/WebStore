package com.training.web_store.command.impl;

import com.training.web_store.command.Command;
import com.training.web_store.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrongCommand implements Command {
    private static final String ERROR_PAGE = "/error";

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Redirector.redirect(response, ERROR_PAGE);
    }
}
