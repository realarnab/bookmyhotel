package com.bookmyhotel.exceptions;

public class PropertyUserNotFoundException extends RuntimeException{
    public PropertyUserNotFoundException(String message) {
        super(message);
    }
}
