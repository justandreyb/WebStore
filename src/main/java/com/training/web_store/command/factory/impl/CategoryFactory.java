package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.category.*;

import java.util.HashMap;

public class CategoryFactory extends EntityFactory {

    public CategoryFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.ADD, new AddCategoryCommand());
        repository.put(CommandName.EDIT, new EditCategoryCommand());
        repository.put(CommandName.DELETE, new DeleteCategoryCommand());

        repository.put(CommandName.GET_ENTITY, new GetCategoryCommand());
        repository.put(CommandName.GET_ENTITIES, new GetCategoriesCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
