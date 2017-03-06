package com.training.web_store.dao.impl.store;

import com.training.web_store.bean.store.Thing;
import com.training.web_store.dao.ThingDAO;
import com.training.web_store.dao.exception.DAOException;

import java.sql.Date;

public class ThingDAOImpl implements ThingDAO {
    //TODO: Write
    @Override
    public void addThing(Thing thing) throws DAOException {

    }

    @Override
    public void addThingReview(int thingId, String review) throws DAOException {

    }

    @Override
    public void addRating(int userId, int thingId, byte value) throws DAOException {

    }

    @Override
    public void updateThing(int thingId, String name, String description, Date creationDate) throws DAOException {

    }

    @Override
    public Thing getThing(int thingId) throws DAOException {
        return null;
    }

    @Override
    public byte getRating(int thingId) throws DAOException {
        return 0;
    }

    @Override
    public void setThingAvailable(int thingId, boolean available) throws DAOException {

    }
}
