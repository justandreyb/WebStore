package com.training.pages_creator.util.exception;

public class UtilException extends Exception {
    public UtilException() {}

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(String message, Exception exception) {
        super(message, exception);

    }
}
