package com.training.pages_creator.command.exception;

public class CommandException extends Exception {
    public CommandException() {}

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(String message, Exception exception) {
        super(message, exception);

    }
}
