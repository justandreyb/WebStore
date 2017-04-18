package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.thing.*;

import java.util.HashMap;

public class ThingFactory extends EntityFactory {

    public ThingFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.ADD, new AddThingCommand());
        repository.put(CommandName.EDIT, new EditThingCommand());
        repository.put(CommandName.DELETE, new DeleteThingCommand());

        repository.put(CommandName.ADD_TO_PRODUCT, new AddThingToProductCommand());
        repository.put(CommandName.DELETE_FROM_PRODUCT, new DeleteThingFromProductCommand());

        repository.put(CommandName.GET_ENTITY, new GetThingCommand());
        repository.put(CommandName.GET_ENTITIES, new GetThingsCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }

}
