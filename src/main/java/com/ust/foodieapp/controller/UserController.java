package com.ust.foodieapp.controller;

import com.ust.foodieapp.exception.UserNotFoundException;
import com.ust.foodieapp.model.User;
import com.ust.foodieapp.model.UserDto;
import com.ust.foodieapp.service.JwtUserDetailsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserController {
    @Autowired
    private JwtUserDetailsService service;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    /* updating user password */

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUserDetails(@Valid @RequestBody User user,@PathVariable int id) throws UserNotFoundException {
        logger.info("User account updated");
        return new ResponseEntity<>(service.updateUser(user,id), HttpStatus.CREATED);
    }

    /* remove user account */

    @DeleteMapping("/user/remove/{id}")
    public ResponseEntity<User> deleteUserDetails(@PathVariable int id) throws UserNotFoundException {
        logger.info("Customer details deleted");
        return new ResponseEntity<>(service.deleteUser(id),HttpStatus.OK);
    }

    /* getting user details by user Id */

    @GetMapping("/user/{id}")
    public ResponseEntity<User> displayUserDetails(@PathVariable int id) throws UserNotFoundException {
        logger.info("User account fetched by id");
        return new ResponseEntity<>(service.getUserById(id),HttpStatus.FOUND);
    }
}
