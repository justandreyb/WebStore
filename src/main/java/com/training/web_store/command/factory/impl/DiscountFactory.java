package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.brand.EditBrandCommand;
import com.training.web_store.command.impl.store.discount.AddDiscountCommand;
import com.training.web_store.command.impl.store.discount.DeleteDiscountCommand;
import com.training.web_store.command.impl.store.discount.GetDiscountCommand;
import com.training.web_store.command.impl.store.discount.GetDiscountsCommand;

import java.util.HashMap;

public class DiscountFactory extends EntityFactory {

    public DiscountFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.ADD, new AddDiscountCommand());
        repository.put(CommandName.EDIT, new EditBrandCommand());
        repository.put(CommandName.DELETE, new DeleteDiscountCommand());

        repository.put(CommandName.GET_ENTITY, new GetDiscountCommand());
        repository.put(CommandName.GET_ENTITIES, new GetDiscountsCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
