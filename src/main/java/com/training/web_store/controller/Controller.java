package com.training.web_store.controller;

import com.training.util.Redirector;
import com.training.util.ResponseWriter;
import com.training.web_store.command.Command;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.factory.FactoryProducer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);
    private static final String COMMAND_PARAMETER = "command";
    private static final String ENTITY_PARAMETER = "entity";
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
        log.debug(request.getRequestURI());
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
        //TODO: Refactor
            String requestedCommand = getCommandFromRequest(request);
            String entity = (String) request.getAttribute(ENTITY_PARAMETER);

            FactoryProducer factoryProducer = FactoryProducer.getInstance();
            EntityFactory factory = factoryProducer.getFactory(entity);
        //

        try {
            Command command = factory.getCommand(requestedCommand);
            command.execute(request, response);

        } catch (Exception e) {
            String errorMessage = "Error while executing command";
            log.warn(errorMessage, e);
            ResponseWriter.writeError(response, errorMessage);
        }

    }

}
