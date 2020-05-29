package com.emlakjet.emlakjetchallenge.exception;

public class EstateAgencyNotFoundException extends RuntimeException {
    public EstateAgencyNotFoundException(String message) {
        super(message);
    }

    public EstateAgencyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
