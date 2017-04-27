package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.role.GetRolesCommand;

import java.util.HashMap;

public class RoleFactory extends EntityFactory {
    public RoleFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.GET_ENTITIES, new GetRolesCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
