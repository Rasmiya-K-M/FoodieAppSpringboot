package com.ust.foodieapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private int uId;
    private String firstName;
    private String lastName;
    private String username;
    private String contactNum;
    private String address;
    private String password;

}
