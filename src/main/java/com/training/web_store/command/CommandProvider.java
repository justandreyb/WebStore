package com.training.web_store.command;

import com.training.web_store.command.impl.ChangeLocaleCommand;
import com.training.web_store.command.impl.store.customer.*;
import com.training.web_store.command.impl.store.product.add.*;
import com.training.web_store.command.impl.store.product.remove.*;
import com.training.web_store.command.impl.store.product.update.*;
import com.training.web_store.command.impl.user.SignUpCommand;
import com.training.web_store.command.impl.user.SignInCommand;
import com.training.web_store.command.impl.user.SignOutCommand;
import com.training.web_store.command.impl.WrongCommand;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private static final Logger log = Logger.getLogger(CommandProvider.class.getName());

    private HashMap<CommandName, Command> repository;

    private CommandProvider() {
        repository = new HashMap<CommandName, Command>(20);

        repository.put(CommandName.SIGN_UP, new SignUpCommand());
        repository.put(CommandName.SIGN_IN, new SignInCommand());
        repository.put(CommandName.SIGN_OUT, new SignOutCommand());

        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());

        repository.put(CommandName.CREATE_BRAND, new CreateBrandCommand());
        repository.put(CommandName.CREATE_CATEGORY, new CreateCategoryCommand());
        repository.put(CommandName.CREATE_THING, new CreateThingCommand());
        repository.put(CommandName.CREATE_REVIEW, new CreateReviewCommand());
        repository.put(CommandName.ADD_PHOTO, new AddPhotoCommand());
        repository.put(CommandName.CREATE_PRODUCT, new CreateProductCommand());
        repository.put(CommandName.ADD_TO_PRODUCT, new AddToProductCommand());
        repository.put(CommandName.REMOVE_FROM_PRODUCT, new RemoveFromProductCommand());
        repository.put(CommandName.CREATE_DISCOUNT, new CreateDiscountCommand());

        repository.put(CommandName.SHOW_PRODUCT, new ShowProductCommand());
        repository.put(CommandName.SEARCH_PRODUCT, new SearchProductCommand());
        repository.put(CommandName.SHOW_PRODUCTS, new ShowProductsCommand());

        repository.put(CommandName.UPDATE_PRODUCT, new UpdateProductCommand());
        repository.put(CommandName.UPDATE_THING, new UpdateThingCommand());
        repository.put(CommandName.UPDATE_CATEGORY, new UpdateCategoryCommand());
        repository.put(CommandName.UPDATE_BRAND, new UpdateBrandCommand());
        repository.put(CommandName.UPDATE_DISCOUNT, new UpdateDiscountCommand());

        repository.put(CommandName.REMOVE_PRODUCT, new RemoveProductCommand());
        repository.put(CommandName.REMOVE_THING, new RemoveThingCommand());
        repository.put(CommandName.REMOVE_CATEGORY, new RemoveCategoryCommand());
        repository.put(CommandName.REMOVE_BRAND, new RemoveBrandCommand());
        repository.put(CommandName.REMOVE_DISCOUNT, new RemoveDiscountCommand());

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

            log.info("command " + commandName.toString());
        } catch (IllegalArgumentException ex) {
            command = repository.get(CommandName.WRONG);
            log.log(Level.WARNING, "Wrong command", ex);
        }

        return command;
    }
}
