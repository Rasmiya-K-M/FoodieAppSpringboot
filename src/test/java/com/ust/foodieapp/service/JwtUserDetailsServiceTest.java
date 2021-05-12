package com.ust.foodieapp.service;

import com.ust.foodieapp.exception.UserAlreadyExistsException;
import com.ust.foodieapp.exception.UserNotFoundException;
import com.ust.foodieapp.model.User;
import com.ust.foodieapp.model.UserDto;
import com.ust.foodieapp.repository.UserRepository;
import com.ust.foodieapp.service.JwtUserDetailsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private JwtUserDetailsService service;
    private User user,user1;
    private UserDto userDto;
    private List<User> userList;
    private Optional optional;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        user=new User(1,"raj","krishna","rajkrishna@gmail.com","7799887766","Trissur","password1");
        user1=new User(2,"remya","m","remya11@gmail.com","9988776655","ekm","password2");
        optional=Optional.of(user);
    }

    @AfterEach
    void tearDown() {
        user=null;
        user1=null;
        userDto=null;
    }

    @Test
    void givenCustomerIdToDeleteThenShouldReturnDeletedCustomer() throws UserNotFoundException {
        when(repository.findById(user.getUId())).thenReturn(optional);
        User deleted=repository.findById(user.getUId()).get();
        service.deleteUser(user.getUId());
        assertEquals(1,deleted.getUId());
        //verify(repository,times(2)).findById(soulmate.getId());
        verify(repository,times(1)).deleteById(user.getUId());
    }

    @Test
    public void givenCustomerIdReturnCustomer() throws UserNotFoundException {
        when(repository.findById(anyInt())).thenReturn(optional);
        User user2=service.getUserById(user.getUId());;
        verify(repository,times(2)).findById(anyInt());
    }
    @Test
    public void givenCustomerIdShouldNotReturnCustomer() throws UserNotFoundException {
        when(repository.findById(anyInt())).thenReturn(optional);
        User user2=service.getUserById(user.getUId());;
        verify(repository,times(2)).findById(anyInt());
    }
}