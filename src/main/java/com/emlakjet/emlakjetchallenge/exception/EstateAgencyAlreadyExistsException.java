package com.emlakjet.emlakjetchallenge.exception;

public class EstateAgencyAlreadyExistsException extends RuntimeException {
    public EstateAgencyAlreadyExistsException(String message) {
        super(message);
    }

    public EstateAgencyAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
