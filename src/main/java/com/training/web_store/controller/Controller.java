package com.training.web_store.controller;

import com.training.web_store.controller.impl.CommandProvider;
import com.training.web_store.util.Redirector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class.getName());
    private static final String COMMAND_PARAMETER = "command";

    public Controller() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        analyzeRequest(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        analyzeRequest(request, response);
    }

    private void analyzeRequest(HttpServletRequest request, HttpServletResponse response) {
        if (hasCommand(request)) {
            //response.setCharacterEncoding("utf-8");
            performCommand(request, response);
        } else {
            Redirector.forward(request, response, request.getRequestURI());
        }
    }

    private boolean hasCommand(HttpServletRequest request) {
        String command = request.getParameter(COMMAND_PARAMETER);
        if (command == null) {
            command = (String) request.getAttribute(COMMAND_PARAMETER);
        }

        return command != null;
    }

    private void performCommand(HttpServletRequest request, HttpServletResponse response) {
        String requestedCommand = request.getParameter(COMMAND_PARAMETER);

        if (requestedCommand == null) {
            requestedCommand = (String) request.getAttribute(COMMAND_PARAMETER);
        }

        CommandProvider provider = CommandProvider.getInstance();
        Command command = provider.getCommand(requestedCommand);

        try {
            command.execute(request, response);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error while executing command", e);
        }
    }
}
