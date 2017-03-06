package com.training.web_store.dao;

import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;

public interface ThingDAO {
    void addThing(Thing thing) throws DAOException;
    void addThingReview(int thingId, String review) throws DAOException;
    void addRating(int userId, int thingId, byte value) throws DAOException;

    void updateThing(int thingId, String name, String description, Date creationDate) throws DAOException;

    Thing getThing(int thingId) throws DAOException;
    byte getRating(int thingId) throws DAOException;

    void setThingAvailable(int thingId, boolean available) throws DAOException;
}
