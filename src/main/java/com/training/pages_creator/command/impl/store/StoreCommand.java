package com.training.pages_creator.command.impl.store;

import com.training.pages_creator.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class StoreCommand implements Command {
    protected static final Logger log = Logger.getLogger(StoreCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) { }
}
