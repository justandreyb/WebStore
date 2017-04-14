package com.training.web_store.dao;

import com.training.web_store.bean.store.Photo;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public interface PhotoDAO {
    void addThingPhoto(int thingId, String realName, String href) throws DAOException, StorageException;

    void addProductPhoto(int productId, String realName, String href) throws DAOException, StorageException;

    List<Photo> getPhotosForThing(int thingId) throws DAOException, StorageException;
    List<Photo> getPhotosForProduct(int productId) throws DAOException, StorageException;

    void setAvailable(int photoId, boolean available) throws DAOException, StorageException;
}
