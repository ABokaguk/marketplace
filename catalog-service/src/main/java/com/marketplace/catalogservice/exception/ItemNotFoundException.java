package com.marketplace.catalogservice.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
