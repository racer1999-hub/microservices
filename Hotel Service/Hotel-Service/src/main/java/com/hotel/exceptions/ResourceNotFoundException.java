package com.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Hotel Not found with given Id!!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
