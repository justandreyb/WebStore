package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.brand.*;

import java.util.HashMap;

public class BrandFactory extends EntityFactory {

    public BrandFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.ADD, new AddBrandCommand());
        repository.put(CommandName.EDIT, new EditBrandCommand());
        repository.put(CommandName.DELETE, new DeleteBrandCommand());

        repository.put(CommandName.GET_ENTITY, new GetBrandCommand());
        repository.put(CommandName.GET_ENTITIES, new GetBrandsCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
