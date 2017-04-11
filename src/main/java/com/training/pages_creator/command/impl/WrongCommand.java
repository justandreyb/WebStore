package com.training.pages_creator.command.impl;

import com.training.pages_creator.command.Command;
import com.training.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrongCommand implements Command {

    private static final String ROOT = "/WEB-INF/jsp/templates/forms/";
    private static final String WRONG_FORM = "wrong-form";

    public void execute(HttpServletRequest request, HttpServletResponse response, String entity) {
        Redirector.forward(request, response, ROOT, WRONG_FORM);
    }
}
