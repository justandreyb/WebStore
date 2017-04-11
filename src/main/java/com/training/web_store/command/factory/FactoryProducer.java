package com.training.web_store.command.factory;

import com.training.web_store.command.factory.impl.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class FactoryProducer {

    private static final Logger log = Logger.getLogger(FactoryProducer.class.getName());
    private static final FactoryProducer instance = new FactoryProducer();
    private static final int FACTORIES_COUNT = 9;

    private Map<FactoryName, EntityFactory> repository;

    private FactoryProducer() {
        repository = new HashMap<FactoryName, EntityFactory>(FACTORIES_COUNT);

        repository.put(FactoryName.ACCOUNT, new AccountFactory());
        repository.put(FactoryName.BRAND, new BrandFactory());
        repository.put(FactoryName.CATEGORY, new CategoryFactory());
        repository.put(FactoryName.DISCOUNT, new DiscountFactory());
        repository.put(FactoryName.PHOTO, new PhotoFactory());
        repository.put(FactoryName.PRODUCT, new ProductFactory());
        repository.put(FactoryName.REVIEW, new ReviewFactory());
        repository.put(FactoryName.THING, new ThingFactory());

        repository.put(FactoryName.WRONG, new WrongFactory());
    }

    public static FactoryProducer getInstance() {
        return instance;
    }

    public EntityFactory getFactory(String requestedFactory) {
        FactoryName factoryName;
        EntityFactory factory;

        try {
            factoryName = FactoryName.valueOf(requestedFactory.toUpperCase());
            factory = repository.get(factoryName);

        } catch (IllegalArgumentException ex) {
            factory = repository.get(FactoryName.WRONG);
            log.debug("Wrong factory");
        }

        return factory;
    }
}