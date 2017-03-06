package com.training.web_store.dao;

import com.training.web_store.bean.store.Photo;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public interface PhotoDAO {
    void addPhoto(int thingId, String realName, String href) throws DAOException;

    List<Photo> getPhotos(int thingId) throws DAOException;

    void markPhotoAsDeleted(int photoId) throws DAOException;
}
