package com.training.pages_creator.controller;

import com.training.pages_creator.bean.ParsedRequest;
import com.training.pages_creator.command.Command;
import com.training.pages_creator.command.factory.CommandRepository;
import com.training.pages_creator.controller.util.URIParser;
import com.training.pages_creator.controller.util.exception.UtilException;
import com.training.util.ResponseWriter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagesController extends HttpServlet {
    private static final Logger log = Logger.getLogger(PagesController.class.getName());

    public PagesController() {
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
        try {
            ParsedRequest parsedRequest = URIParser.parseRequest(request);

            performTask(parsedRequest, request, response);
        } catch (UtilException e) {
            log.warn("Cannot be processed", e);
        }
    }

    private void performTask(ParsedRequest parsedRequest, HttpServletRequest request, HttpServletResponse response) {

        try {
            CommandRepository factory = CommandRepository.getInstance();
            String entity = parsedRequest.getEntity();
            String requestedCommand = parsedRequest.getCommand();

            Command command = factory.getCommand(requestedCommand);

            command.execute(request, response, entity);
        } catch (Exception e) {
            String errorMessage = "Error while executing command";
            log.warn(errorMessage, e);
            ResponseWriter.writeError(response, errorMessage);
        }
    }

}
