package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.image.AddImageCommand;
import com.training.web_store.command.impl.store.image.DeleteImageCommand;
import com.training.web_store.command.impl.store.image.GetImageCommand;
import com.training.web_store.command.impl.store.image.GetImagesCommand;

import java.util.HashMap;

public class PhotoFactory extends EntityFactory {

    public PhotoFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.ADD, new AddImageCommand());
        repository.put(CommandName.DELETE, new DeleteImageCommand());

        repository.put(CommandName.GET_ENTITY, new GetImageCommand());
        repository.put(CommandName.GET_ENTITIES, new GetImagesCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
