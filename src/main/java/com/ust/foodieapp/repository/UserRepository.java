package com.ust.foodieapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ust.foodieapp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    //find user by username

    User findByUsername(String username);

    //exist user by  username

    boolean existsByusername(String username);
}
