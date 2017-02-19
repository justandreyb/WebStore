package com.training.web_store.controller.impl;

import com.training.web_store.bean.CommandName;
import com.training.web_store.controller.Command;
import com.training.web_store.controller.impl.command.user.SignUpCommand;
import com.training.web_store.controller.impl.command.user.SignInCommand;
import com.training.web_store.controller.impl.command.user.SignOutCommand;
import com.training.web_store.controller.impl.command.WrongCommand;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private static final Logger log = Logger.getLogger(CommandProvider.class.getName());

    private HashMap<CommandName, Command> repository;

    private CommandProvider() {
        repository = new HashMap<CommandName, Command>(2);

        repository.put(CommandName.SIGN_UP, new SignUpCommand());
        repository.put(CommandName.SIGN_IN, new SignInCommand());
        repository.put(CommandName.SIGN_OUT, new SignOutCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String request) {
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(request.toUpperCase());
            command = repository.get(commandName);

            log.info("command " + commandName.toString());
        } catch (IllegalArgumentException ex) {
            command = repository.get(CommandName.WRONG);
            log.log(Level.WARNING, "Wrong command", ex);
        }

        return command;
    }
}
