package com.emlakjet.emlakjetchallenge.exception;

public class EstateAlreadyExistsException extends RuntimeException {
    public EstateAlreadyExistsException(String message) {
        super(message);
    }

    public EstateAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
