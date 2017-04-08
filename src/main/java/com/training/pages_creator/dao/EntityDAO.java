package com.training.pages_creator.dao;

import com.training.pages_creator.bean.CollectedData;
import com.training.pages_creator.dao.exception.DAOException;

import java.util.List;

public interface EntityDAO {
    CollectedData getObject(int objectId) throws DAOException;
    List<CollectedData> getObjects() throws DAOException;
    List<CollectedData> getObjects(int objectId) throws DAOException;
}
