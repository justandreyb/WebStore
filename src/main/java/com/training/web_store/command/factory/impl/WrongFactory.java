package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;

import java.util.HashMap;

public class WrongFactory extends EntityFactory {

    public WrongFactory() {
        repository = new HashMap<>();
        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
