package com.training.pages_creator.dao.factory;

import com.training.pages_creator.dao.EntityDAO;
import com.training.pages_creator.dao.EntityName;
import com.training.pages_creator.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private static final DAOFactory factory = new DAOFactory();

    private Map<EntityName, EntityDAO> entities;

    private DAOFactory() {
        entities = new HashMap<>();

        entities.put(EntityName.BRAND, new BrandDAOImpl());
        entities.put(EntityName.CATEGORY, new CategoryDAOImpl());
        entities.put(EntityName.DISCOUNT, new DiscountDAOImpl());
        entities.put(EntityName.ORDER, new OrderDAOImpl());
        entities.put(EntityName.PHOTO, new PhotoDAOImpl());
        entities.put(EntityName.PRODUCT, new ProductDAOImpl());
        entities.put(EntityName.THING, new ThingDAOImpl());
        entities.put(EntityName.THING_PRODUCT, new ThingProductDAOImpl());

        entities.put(EntityName.WRONG, new WrongDAOImpl());
    }

    public static DAOFactory getInstance() {
        return factory;
    }

    public EntityDAO getEntity(String requestedEntity) {
        EntityName entityName;
        EntityDAO entity;

        try {
            entityName = EntityName.valueOf(requestedEntity.toUpperCase());
            entity = entities.get(entityName);

        } catch (IllegalArgumentException ex) {
            entity = entities.get(EntityName.WRONG);
        }

        return entity;
    }
}
