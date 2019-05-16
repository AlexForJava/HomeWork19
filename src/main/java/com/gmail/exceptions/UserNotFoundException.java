package com.gmail.exceptions;

/**
 * Created by Space on 09.05.2019.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
