package com.training.util.exception;

public class ProjectUtilException extends Exception {
    public ProjectUtilException() {}

    public ProjectUtilException(String message) {
        super(message);
    }

    public ProjectUtilException(Throwable cause) {
        super(cause);
    }

    public ProjectUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectUtilException(String message, Exception exception) {
        super(message, exception);

    }
}
