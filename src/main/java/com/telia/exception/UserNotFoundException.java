package com.telia.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("Something is wrong, can't find user: %s", username));
    }
}
