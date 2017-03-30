package com.training.pages_creator.controller;

import com.training.pages_creator.bean.ParsedRequest;
import com.training.pages_creator.command.Command;
import com.training.pages_creator.command.factory.EntityCommandFactory;
import com.training.pages_creator.command.factory.FactoryProducer;
import com.training.pages_creator.util.URIParser;
import com.training.pages_creator.util.exception.UtilException;
import com.training.util.AnswerCreator;
import com.training.util.ResponseWriter;
import com.training.util.exception.ProjectUtilException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
            String uri = request.getRequestURI();
            ParsedRequest parsedRequest = URIParser.parseRequest(uri);

            performTask(parsedRequest, request, response);
        } catch (UtilException e) {
            log.warn("Cannot be processed", e);
        }
    }

    private void performTask(ParsedRequest parsedRequest, HttpServletRequest request, HttpServletResponse response) {

        try {
            FactoryProducer factoryProducer = FactoryProducer.getInstance();
            String entity = parsedRequest.getEntity();
            String requestedCommand = parsedRequest.getCommand();

            EntityCommandFactory factory = factoryProducer.getFactory(entity);
            Command command = factory.getCommand(requestedCommand);

            command.execute(request, response);
        } catch (Exception e) {
            String errorMessage = "Error while executing command";
            log.warn(errorMessage, e);
            String answer = AnswerCreator.createError(errorMessage);
            try {
                ResponseWriter.write(response, answer);
            } catch (ProjectUtilException innerE) {
                log.fatal("Error while writing in response", innerE);
            }
        }
    }

}
