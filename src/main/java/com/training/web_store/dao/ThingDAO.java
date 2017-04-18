package com.training.web_store.dao;

import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.sql.Date;
import java.util.List;

public interface ThingDAO {
    void addThing(Thing thing, int categoryId, int brandId) throws DAOException, StorageException;

    void updateThing(int thingId, String name, String description, Date creationDate, int categoryId, int brandId) throws DAOException, StorageException;

    Thing getThing(int thingId) throws DAOException, StorageException;
    List<Thing> getThings() throws DAOException, StorageException;
    List<Thing> getThingsForProduct(int productId) throws DAOException, StorageException;

    void setThingAvailable(int thingId, boolean available) throws DAOException, StorageException;

    void addRating(int userId, int thingId, byte value) throws DAOException, StorageException;
    byte getRating(int thingId) throws DAOException, StorageException;
    void deleteRating(int thingId, int userId) throws DAOException, StorageException;

    void addThingReview(int thingId, String review) throws DAOException, StorageException;
    String getThingReview(int thingId) throws DAOException, StorageException;
    void deleteThingReview(int thingId) throws DAOException, StorageException;
}
