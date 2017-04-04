package com.training.pages_creator.service.impl;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.exception.DAOException;
import com.training.pages_creator.dao.factory.DAOFactory;
import com.training.pages_creator.service.ViewService;
import com.training.pages_creator.service.exception.ServiceException;

import java.util.List;

public class ViewServiceImpl implements ViewService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public CollectedData getEntity(String entity, int entityId, String formName) throws ServiceException {
        EntityDAO entityDAO = daoFactory.getEntity(entity);
        try {
            return entityDAO.getObject(entityId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CollectedData> getEntities(String entity, String formName) throws ServiceException {
        EntityDAO entityDAO = daoFactory.getEntity(entity);
        try {
            return entityDAO.getObjects();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
