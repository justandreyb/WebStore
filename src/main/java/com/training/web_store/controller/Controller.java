package com.training.web_store.controller;

import com.training.web_store.command.Command;
import com.training.web_store.command.CommandProvider;
import com.training.web_store.util.Redirector;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class.getName());
    private static final String COMMAND_PARAMETER = "command";
    private static final String ERROR = "error";
    private static final String ERROR_INFO = "Command cannot be executed right now.";

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
        System.out.println(request.getRequestURI());
        if (hasCommand(request)) {
            performCommand(request, response);
        } else {
            String uri = request.getRequestURI();
            Redirector.forward(request, response, uri);
        }
    }

    private String getCommandFromRequest(HttpServletRequest request) {
        String command = request.getParameter(COMMAND_PARAMETER);
        if (command == null) {
            command = (String) request.getAttribute(COMMAND_PARAMETER);
        }
        return command;
    }

    private boolean hasCommand(HttpServletRequest request) {
        return getCommandFromRequest(request) != null;
    }

    private void performCommand(HttpServletRequest request, HttpServletResponse response) {
        String requestedCommand = getCommandFromRequest(request);

        CommandProvider provider = CommandProvider.getInstance();

        try {
            Command command = provider.getCommand(requestedCommand);
            command.execute(request, response);

        } catch (Exception e) {
            log.log(Level.SEVERE, "Error while executing command", e);
            String answer = createError();
            try {
                response.getWriter().write(answer);
            } catch (IOException innerE) {
                log.log(Level.SEVERE, "Error while writing in response", innerE);
            }
        }

    }

    private String createError() {
        JSONObject answerJSON = new JSONObject();
        answerJSON.put(ERROR, ERROR_INFO);
        return answerJSON.toString();
    }

}
