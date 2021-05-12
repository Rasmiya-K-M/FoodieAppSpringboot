package com.ust.foodieapp.exception;

import lombok.NoArgsConstructor;

/**
 * exception handling for
 * customer already exists
 * */

@NoArgsConstructor
public class UserAlreadyExistsException extends Exception{
    private String msg;

    public UserAlreadyExistsException(String msg,String message) {
        super(message);
        this.msg=msg;
    }
}
