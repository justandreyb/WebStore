package com.training.web_store.command.factory.impl;

import com.training.web_store.command.CommandName;
import com.training.web_store.command.factory.EntityFactory;
import com.training.web_store.command.impl.WrongCommand;
import com.training.web_store.command.impl.store.review.AddReviewCommand;
import com.training.web_store.command.impl.store.review.DeleteReviewCommand;
import com.training.web_store.command.impl.store.review.GetReviewCommand;
import com.training.web_store.command.impl.store.review.GetReviewsCommand;

import java.util.HashMap;

public class ReviewFactory extends EntityFactory {

    public ReviewFactory() {
        repository = new HashMap<>();

        repository.put(CommandName.ADD, new AddReviewCommand());
        repository.put(CommandName.DELETE, new DeleteReviewCommand());

        repository.put(CommandName.GET_ENTITY, new GetReviewCommand());
        repository.put(CommandName.GET_ENTITIES, new GetReviewsCommand());

        repository.put(CommandName.WRONG, new WrongCommand());
    }
}
