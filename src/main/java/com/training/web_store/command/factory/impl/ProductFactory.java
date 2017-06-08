package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.product.*;

import java.util.HashMap;

public class ProductFactory extends EntityFactory {

    public ProductFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.ADD, new AddProductCommand());
        repository.put(CommandName.EDIT, new EditProductCommand());
        repository.put(CommandName.DELETE, new DeleteProductCommand());

        repository.put(CommandName.GET_ENTITY, new GetProductCommand());
        repository.put(CommandName.GET_ENTITIES, new GetProductsCommand());

        repository.put(CommandName.SHOW, new ShowProductCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
