package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.interaction.ChangeLocaleCommand;
import com.training.web_store.command.impl.interaction.SearchProductCommand;
import com.training.web_store.command.impl.interaction.SetRatingCommand;
import com.training.web_store.command.impl.interaction.order.AddToOrderCommand;
import com.training.web_store.command.impl.interaction.order.BuyOrderCommand;
import com.training.web_store.command.impl.interaction.order.GetOrderCommand;
import com.training.web_store.command.impl.interaction.order.RemoveFromOrderCommand;
import com.training.web_store.command.impl.user.GetAccountsCommand;
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

        repository.put(CommandName.GET_ENTITIES, new GetAccountsCommand());

        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());

        repository.put(CommandName.SET, new SetRatingCommand());
        repository.put(CommandName.ADD_TO_ORDER, new AddToOrderCommand());
        repository.put(CommandName.REMOVE_FROM_ORDER, new RemoveFromOrderCommand());
        repository.put(CommandName.GET_ORDER, new GetOrderCommand());
        repository.put(CommandName.BUY_ORDER, new BuyOrderCommand());
        repository.put(CommandName.SEARCH_PRODUCT, new SearchProductCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
