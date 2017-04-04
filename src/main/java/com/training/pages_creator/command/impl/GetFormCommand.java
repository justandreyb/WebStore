package com.training.pages_creator.command.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.service.ViewService;
import com.training.pages_creator.service.exception.ServiceException;
import com.training.pages_creator.service.factory.ServiceFactory;
import com.training.util.Redirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetFormCommand extends BaseCommand {

    private static final String ACTION = "action";
    private static final String ID = "id";
    private static final String ENTITY = "entity";
    private static final String ENTITIES = "entities";
    private static final String WRONG_FORM = "wrong-form";
    private static final String ROOT = "/WEB-INF/jsp/templates/forms/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String entity) {
        if (isRequestForSingleEntity(request)) {
            executeGetEntity(request, response, entity, viewService);
        } else {
            executeGetEntities(request, response, entity, viewService);
        }
    }

    private boolean isRequestForSingleEntity(HttpServletRequest request) {
        String entityId = request.getParameter(ID);
        return entityId != null;
    }

    private void executeGetEntities(HttpServletRequest request, HttpServletResponse response, String entity,
                                    ViewService service) {
        try {
            String formName = request.getParameter(ACTION);

            if (formName != null) {
                List<CollectedData> entities = service.getEntities(entity, formName);
                request.setAttribute(ENTITIES, entities);
                Redirector.forward(request, response, ROOT, formName);
            }
        } catch (ServiceException e) {
//                TODO: Catch
            Redirector.forward(request, response, ROOT, WRONG_FORM);
        }
    }

    private void executeGetEntity(HttpServletRequest request, HttpServletResponse response, String entity,
                                  ViewService service) {
        try {
            String formName = request.getParameter(ACTION);
            int entityId = Integer.parseInt(request.getParameter(ID));
            if (formName != null) {
                CollectedData collectedEntity = service.getEntity(entity, entityId, formName);
                request.setAttribute(ENTITY, collectedEntity);
                Redirector.forward(request, response, ROOT, formName);
            }
        } catch (ServiceException | NumberFormatException e) {
//                TODO: Catch
            Redirector.forward(request, response, ROOT, WRONG_FORM);
        }
    }
}
