package com.emlakjet.emlakjetchallenge.exception;

public class EstateNotFoundException extends RuntimeException {
    public EstateNotFoundException(String message) {
        super(message);
    }

    public EstateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
