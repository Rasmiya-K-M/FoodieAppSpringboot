package com.ust.foodieapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_table")
public class User {
    //properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    @NotNull
    @Size(min = 3,max = 30,message = "The firstname should not blank and must contain minimum 3 character and max 30 character")
    private String firstName;
    private String lastName;
    @Email
    @NotBlank(message = "The username cannot be null")
    private String username;
    @NotNull(message = "The contactNum cannot be null")
    @Size(min=10,max=10, message = "should have 10 digits")
    private String contactNum;
    @NotNull(message = "Address is mandatory")
    @NotBlank(message = "Address should not be blank")
    private String address;
    @NotNull(message = "required")
    @Size(min = 5,message = "password should have minimum size of 5 ")
    private String password;

}
