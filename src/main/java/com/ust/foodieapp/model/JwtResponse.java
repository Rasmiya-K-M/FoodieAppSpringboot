package com.ust.foodieapp.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;

/*
* jwt token response
* */

@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;


    public String getToken() {
        return this.jwttoken;
    }
}
