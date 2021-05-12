package com.ust.foodieapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.foodieapp.controller.UserController;
import com.ust.foodieapp.exception.UserNotFoundException;
import com.ust.foodieapp.exception.GlobalException;
import com.ust.foodieapp.model.User;
import com.ust.foodieapp.model.UserDto;
import com.ust.foodieapp.service.JwtUserDetailsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JwtUserDetailsService service;
    private User user;
    private UserDto userDto;
    private List<User> userList;

    @InjectMocks
    private UserController controller;

    @BeforeEach
    void setUp() {
        user=new User(1,"rasmiya","m","rasmiya@gmail.com","9061812909","trissur","password1");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalException()).build();
        userList=new ArrayList<>();
        userList.add(user);
    }

    @AfterEach
    void tearDown() {
        user=null;
    }

    
    @Test
    public  void givenCustomerIdShouldReturnCustomer() throws Exception {
        when(service.getUserById(user.getUId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1"))
                .andExpect(MockMvcResultMatchers.status()
                        .isFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void givenCustomerIdToDeleteThenShouldNotReturnDeletedCustomer() throws Exception {
        when(service.deleteUser(anyInt())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(delete("/api/v1/user/remove/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}