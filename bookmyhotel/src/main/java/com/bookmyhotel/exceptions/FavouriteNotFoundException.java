package com.bookmyhotel.exceptions;

public class FavouriteNotFoundException extends RuntimeException{
    public FavouriteNotFoundException(String message) {
        super(message);
    }
}
