package com.training.web_store.command.impl;

import com.training.util.ResponseWriter;
import com.training.web_store.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrongCommand implements Command {
    private static final String ERROR_DOES_NOT_EXISTS = "Command doesn't exists";

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ResponseWriter.writeError(response, ERROR_DOES_NOT_EXISTS);
    }
}
