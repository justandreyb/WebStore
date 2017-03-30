package com.training.pages_creator.command.factory;

import com.training.pages_creator.command.factory.impl.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class FactoryProducer {

    private static final Logger log = Logger.getLogger(FactoryProducer.class.getName());
    private static final FactoryProducer instance = new FactoryProducer();
    private static final int factoriesCount = 9;

    private Map<FactoryName, EntityCommandFactory> repository;

    private FactoryProducer() {
        repository = new HashMap<FactoryName, EntityCommandFactory>(factoriesCount);

        repository.put(FactoryName.ACCOUNT, new AccountCommandFactory());
        repository.put(FactoryName.BRAND, new BrandCommandFactory());
        repository.put(FactoryName.CATEGORY, new CategoryCommandFactory());
        repository.put(FactoryName.DISCOUNT, new DiscountCommandFactory());
        repository.put(FactoryName.PHOTO, new PhotoCommandFactory());
        repository.put(FactoryName.PRODUCT, new ProductCommandFactory());
        repository.put(FactoryName.REVIEW, new ReviewCommandFactory());
        repository.put(FactoryName.THING, new ThingCommandFactory());

        repository.put(FactoryName.WRONG, new WrongCommandFactory());
    }

    public static FactoryProducer getInstance() {
        return instance;
    }

    public EntityCommandFactory getFactory(String requestedFactory) {
        FactoryName factoryName;
        EntityCommandFactory factory;

        try {
            factoryName = FactoryName.valueOf(requestedFactory.toUpperCase());
            factory = repository.get(factoryName);

        } catch (IllegalArgumentException ex) {
            factory = repository.get(FactoryName.WRONG);
            log.warn("Wrong factory");
        }

        return factory;
    }
}
