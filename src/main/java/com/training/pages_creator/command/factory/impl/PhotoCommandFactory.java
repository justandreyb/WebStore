package com.training.pages_creator.command.factory.impl;

import com.training.pages_creator.command.CommandName;
import com.training.pages_creator.command.factory.EntityCommandFactory;
import com.training.pages_creator.command.impl.WrongCommand;

import java.util.HashMap;

public class PhotoCommandFactory extends EntityCommandFactory {

    public PhotoCommandFactory() {
        repository = new HashMap<>();
        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
