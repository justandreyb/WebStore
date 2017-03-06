package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Photo;
import com.training.web_store.dao.PhotoDAO;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public class PhotoDAOImpl implements PhotoDAO {
    //TODO: Write
    @Override
    public void addPhoto(int thingId, String realName, String href) throws DAOException {

    }

    @Override
    public List<Photo> getPhotos(int thingId) throws DAOException {
        return null;
    }

    @Override
    public void markPhotoAsDeleted(int photoId) throws DAOException {

    }
}
