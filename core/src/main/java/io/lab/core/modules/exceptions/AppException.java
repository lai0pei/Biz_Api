package io.lab.core.modules.exceptions;

//to show every App level message
public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
