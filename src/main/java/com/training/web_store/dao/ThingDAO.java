package com.training.web_store.dao;

import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface ThingDAO {
    void addThing(Thing thing, int categoryId, int brandId) throws DAOException;

    void updateThing(int thingId, String name, String description, Date creationDate, int categoryId, int brandId) throws DAOException;

    Thing getThing(int thingId) throws DAOException;
    List<Thing> getThings() throws DAOException;
    List<Thing> getThingsForProduct(int productId) throws DAOException;

    void setThingAvailable(int thingId, boolean available) throws DAOException;

    void addRating(int userId, int thingId, byte value) throws DAOException;
    byte getRating(int thingId) throws DAOException;
    void deleteRating(int thingId, int userId) throws DAOException;

    void addThingReview(int thingId, String review) throws DAOException;
    String getThingReview(int thingId) throws DAOException;
    void deleteThingReview(int thingId) throws DAOException;
}
