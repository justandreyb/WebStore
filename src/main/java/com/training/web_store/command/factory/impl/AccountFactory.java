package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.interaction.*;
import com.training.web_store.command.impl.user.SignInCommand;
import com.training.web_store.command.impl.user.SignOutCommand;
import com.training.web_store.command.impl.user.SignUpCommand;
import com.training.web_store.command.impl.user.UpdateAccountInfoCommand;

import java.util.HashMap;

public class AccountFactory extends EntityFactory {

    public AccountFactory() {
        repository = new HashMap<>();
        repository.put(CommandName.SIGN_UP, new SignUpCommand());
        repository.put(CommandName.SIGN_IN, new SignInCommand());
        repository.put(CommandName.SIGN_OUT, new SignOutCommand());
        repository.put(CommandName.UPDATE_ACCOUNT, new UpdateAccountInfoCommand());

        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());

        repository.put(CommandName.SET, new SetRatingCommand());
        repository.put(CommandName.ADD_TO_ORDER, new AddToOrderCommand());
        repository.put(CommandName.REMOVE_FROM_ORDER, new RemoveFromOrderCommand());
        repository.put(CommandName.BUY_ORDER, new BuyOrderCommand());
        repository.put(CommandName.SEARCH_PRODUCT, new SearchProductCommand());
        /*
        repository.put(CommandName.SHOW_PRODUCT, new ShowProductCommand());
        repository.put(CommandName.SHOW_PRODUCTS, new ShowProductsCommand());
        */
        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
