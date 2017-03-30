package com.training.pages_creator.command.factory;

import com.training.pages_creator.command.Command;
import com.training.pages_creator.command.CommandName;
import com.training.pages_creator.command.exception.CommandException;

import java.util.Map;

import org.apache.log4j.Logger;

public abstract class EntityCommandFactory {
    protected static final Logger log = Logger.getLogger(CommandException.class.getName());

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
