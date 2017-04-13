package com.training.web_store.dao;

import com.training.web_store.bean.store.Photo;
import com.training.web_store.dao.exception.DAOException;

import java.util.List;

public interface PhotoDAO {
    void addThingPhoto(int thingId, String realName, String href) throws DAOException;

    void addProductPhoto(int productId, String realName, String href) throws DAOException;

    List<Photo> getPhotosForThing(int thingId) throws DAOException;
    List<Photo> getPhotosForProduct(int productId) throws DAOException;

    void setAvailable(int photoId, boolean available) throws DAOException;
}
