package com.training.pages_creator.command.factory;

import com.training.pages_creator.command.Command;
import com.training.pages_creator.command.CommandName;
import com.training.pages_creator.command.exception.CommandException;

import java.util.HashMap;
import java.util.Map;

import com.training.pages_creator.command.impl.GetFormCommand;
import com.training.pages_creator.command.impl.WrongCommand;
import org.apache.log4j.Logger;

public class EntityCommandRepository {
    private static final Logger log = Logger.getLogger(CommandException.class.getName());

    private static final EntityCommandRepository instance = new EntityCommandRepository();

    private Map<CommandName, Command> repository;

    private EntityCommandRepository() {
        repository = new HashMap<CommandName, Command>();

        repository.put(CommandName.GET_FORM, new GetFormCommand());
        repository.put(CommandName.WRONG, new WrongCommand());
    }

    public static EntityCommandRepository getInstance() {
        return instance;
    }

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
            log.debug("Wrong command");
        }

        return command;
    }
}
