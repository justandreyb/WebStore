package com.training.pages_creator.service.exception;

public class ServiceException extends Exception {
    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, Exception exception) {
        super(message, exception);

    }
}
