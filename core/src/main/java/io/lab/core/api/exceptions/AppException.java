package io.lab.core.api.exceptions;

//to show every App level message
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
