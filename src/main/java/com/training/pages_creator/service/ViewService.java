package com.training.pages_creator.service;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.service.exception.ServiceException;

import java.util.List;

public interface ViewService {
    CollectedData getEntity(String entity, int entityId, String formName) throws ServiceException;
    List<CollectedData> getEntities(String entity, String formName) throws ServiceException;
}
