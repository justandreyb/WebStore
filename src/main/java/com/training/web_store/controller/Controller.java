package com.training.web_store.controller;

import com.training.web_store.command.Command;
import com.training.web_store.command.CommandProvider;
import com.training.web_store.util.Redirector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class.getName());
    private static final String COMMAND_PARAMETER = "command";
    private static final String RESOURCES = "resources";

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
            performCommand(request, response);
        } else {
            String uri = request.getRequestURI();
            if (!isResourceURI(uri)) {
                Redirector.forward(request, response, uri);
            } else {
                Redirector.redirect(response, uri);
            }
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

    private boolean isResourceURI(String uri) {
        return uri.contains(RESOURCES);
    }
}
