package com.training.pages_creator.command.impl;

import com.training.pages_creator.command.Command;
import com.training.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFormCommand implements Command {

    private static final String ACTION = "action";
    private static final String ROOT = "/WEB-INF/jsp/templates/forms/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String entity) {
        String formName = request.getParameter(ACTION);
        formName = entity + "/" + formName;

        Redirector.forward(request, response, ROOT, formName);
    }
}
