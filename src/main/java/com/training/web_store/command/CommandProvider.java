package com.training.web_store.command;

import com.training.web_store.command.impl.ChangeLocaleCommand;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.customer.*;
import com.training.web_store.command.impl.store.product.*;
import com.training.web_store.command.impl.user.SignInCommand;
import com.training.web_store.command.impl.user.SignOutCommand;
import com.training.web_store.command.impl.user.SignUpCommand;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private static final Logger log = Logger.getLogger(CommandProvider.class);

    private HashMap<CommandName, Command> repository;

    private CommandProvider() {
        repository = new HashMap<CommandName, Command>(20);

        repository.put(CommandName.SIGN_UP, new SignUpCommand());
        repository.put(CommandName.SIGN_IN, new SignInCommand());
        repository.put(CommandName.SIGN_OUT, new SignOutCommand());

        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());

        repository.put(CommandName.BRAND, new BrandCommand());
        repository.put(CommandName.CATEGORY, new CategoryCommand());
        repository.put(CommandName.ACCOUNT, new AccountCommand());
        repository.put(CommandName.THING, new ThingCommand());
        repository.put(CommandName.REVIEW, new ReviewCommand());
        repository.put(CommandName.PHOTO, new PhotoCommand());
        repository.put(CommandName.PRODUCT, new ProductCommand());
        repository.put(CommandName.DISCOUNT, new DiscountCommand());

        repository.put(CommandName.SHOW_PRODUCT, new ShowProductCommand());
        repository.put(CommandName.SEARCH_PRODUCT, new SearchProductCommand());
        repository.put(CommandName.SHOW_PRODUCTS, new ShowProductsCommand());

        repository.put(CommandName.RATE_THING, new RateThingCommand());
        repository.put(CommandName.ADD_TO_ORDER, new AddToOrderCommand());
        repository.put(CommandName.REMOVE_FROM_ORDER, new RemoveFromOrderCommand());
        repository.put(CommandName.BUY_ORDER, new BuyOrderCommand());

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

            //TODO: Remove
            log.info("command " + commandName.toString());
        } catch (IllegalArgumentException ex) {
            command = repository.get(CommandName.WRONG);
            log.warn("Wrong command", ex);
        }

        return command;
    }
}
