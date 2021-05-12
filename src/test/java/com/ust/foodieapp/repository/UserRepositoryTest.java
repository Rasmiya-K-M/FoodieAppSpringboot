package com.ust.foodieapp.repository;

import com.ust.foodieapp.model.User;
import com.ust.foodieapp.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
      private UserRepository repository;
      private User user;
    @BeforeEach
    void setUp() {
        user=new User(1,"rasmiya","m","rasmiya@gmail.com","9061812909","trisur","password1");

    }

   
    @Test
    public  void  givenCustomerIdShouldDeleteCustomer(){
        User user1=repository.save(user);
        repository.deleteById(user1.getUId());
        Optional optional=repository.findById(user1.getUId());
        assertEquals(Optional.empty(),optional);
    }
    @Test
    public  void  GivenNameShouldReturnCustomer(){

        User user1=repository.save(user);
        User list=repository.findById(user1.getUId()).get();
        assertEquals(user1.getUId(),list.getUId());
        assertEquals(user1.getFirstName(),list.getFirstName());
        assertEquals(user1.getAddress(),list.getAddress());
        assertEquals(user1.getContactNum(),list.getContactNum());

    }
    @Test
    public  void  GivenUserNameShouldReturnCustomer(){
        User user1=repository.save(user);
        User list=repository.findByUsername(user1.getUsername());
        assertEquals(user1.getUId(),list.getUId());
        assertEquals(user1.getFirstName(),list.getFirstName());
        assertEquals(user1.getAddress(),list.getAddress());
        assertEquals(user1.getContactNum(),list.getContactNum());

    }
}