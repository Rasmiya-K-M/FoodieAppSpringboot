package com.ust.foodieapp.exception;

import lombok.NoArgsConstructor;

/*
 * exception handling for
 * customer unknown scenario
 * */

@NoArgsConstructor
public class UserNotFoundException extends Exception{
    private String msg;

    public UserNotFoundException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}
