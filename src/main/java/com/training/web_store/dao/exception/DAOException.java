package com.training.web_store.dao.exception;

public class DAOException extends Exception {
    public DAOException() {}

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message, Exception exception) {
        super(message, exception);
    }
}
