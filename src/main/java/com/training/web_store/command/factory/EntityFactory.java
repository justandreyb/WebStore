package com.training.web_store.command.factory;

import com.training.web_store.command.Command;
import com.training.web_store.command.CommandName;
import org.apache.log4j.Logger;

import java.util.Map;

public abstract class EntityFactory {
    protected static final Logger log = Logger.getLogger(EntityFactory.class.getName());

    protected Map<CommandName, Command> repository;

    public Command getCommand(String requestedCommand) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(requestedCommand.toUpperCase());
            if (repository.containsKey(commandName)) {
                command = repository.get(commandName);
            } else {
                command = repository.get(CommandName.WRONG);
            }

        } catch (IllegalArgumentException ex) {
            command = repository.get(CommandName.WRONG);
            log.warn("Wrong command");
        }

        return command;
    }
}