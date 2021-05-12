package com.ust.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ust.Exception.GlobalExceptionHandler;
import com.ust.Exception.RestuarantAlreadyExistsException;
import com.ust.Exception.RestuarantNotFoundException;
import com.ust.model.Favourite;
import com.ust.repository.FavouriteRepository;
import com.ust.service.FavouriteService;
import com.ust.controller.FavouriteController;
@ExtendWith(MockitoExtension.class)

class ControllerTest {

	 private MockMvc mockMvc;
	    @Mock
	    FavouriteService  favouriteService;
	    @InjectMocks
	    private FavouriteController favouriteController;

	    private Favourite favourite;
	    private List<Favourite> favouriteList;


	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(favouriteController).setControllerAdvice(new GlobalExceptionHandler()).build();
	       favourite = new Favourite();
	       favourite.setId(1);
	       favourite.setUserId("1");
	       favourite.setRestaurantName("Imneet");
	       favourite.setCategory("Sample]forTesting");
	       favourite.setFoodItem("Burger");
	       favourite.setUsername("Raj@gmail.com");
	       favourite.setPrice(200);
	       favouriteList = new ArrayList<>();
	       favouriteList.add(favourite);
	    }

	    @AfterEach
	    void tearDown() {
	    	favourite = null;
	    }

	    @Test
	    void givenFavouriteToSaveThenShouldReturnSavedFavourite() throws Exception {
	        when(favouriteService.saveFavourite(any())).thenReturn(favourite);
	        mockMvc.perform(post("/api/v1//add")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(favourite)))
	                .andExpect(status().isCreated())
	                .andDo(MockMvcResultHandlers.print());
	    }
//	    @Test
//	    void givenFavouriteToSaveThenShouldNotReturnSavedFavourite() throws Exception {
//	        when(favouriteService.saveFavourite((Favourite) any()));
//	        mockMvc.perform(post("/api/v1/add")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content(asJsonString(favourite)))
//	                .andExpect(status().isConflict())
//	                .andDo(MockMvcResultHandlers.print());
//	    }
	    @Test
	    void givenGetAllFavouriteThenShouldReturnListOfAllFavourite() throws Exception {
	        when(favouriteService.getAllFavourite()).thenReturn(favouriteList);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/Favourite")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
	                .andDo(MockMvcResultHandlers.print());
	        verify(favouriteService).getAllFavourite();
	        verify(favouriteService, times(1)).getAllFavourite();

	    }

	    @Test
	    void givenFavouriteIdThenShouldReturnRespectiveFavourite() throws Exception, RestuarantNotFoundException {
	        when(favouriteService.getFavouriteById(favourite.getId())).thenReturn(favourite);
	        mockMvc.perform(get("/api/v1/Favourite/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(favourite)))
	                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());

	    }
	    @Test
	    void givenFavouriteIdToDeleteThenShouldReturnDeletedFavourite() throws Exception {
	        when(favouriteService.deleteFavourite(favourite.getId())).thenReturn(favourite);
	        mockMvc.perform(delete("/api/v1/Favourite/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(favourite)))
	                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	    }

	    @Test
	    void givenFavouriteIdToDeleteThenShouldNotReturnDeletedFavourite() throws RestuarantNotFoundException, Exception {
	        when(favouriteService.deleteFavourite(favourite.getId())).thenThrow(RestuarantNotFoundException.class);
	        mockMvc.perform(delete("/api/v1/Favourite/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(favourite)))
	                .andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());
	    }

	    @Test
	    public void givenFavouriteToUpdateThenShouldReturnUpdatedFavourite() throws Exception {
	        when(favouriteService.updateFavourite(any())).thenReturn(favourite);
	        mockMvc.perform(put("/api/v1/Favourite").contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
	                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	    }

	    @Test
	    public void givenFavouriteToUpdateThenShouldNotReturnUpdatedBlog() throws RestuarantNotFoundException, Exception {
	        when(favouriteService.updateFavourite(any())).thenThrow(RestuarantNotFoundException.class);
	        mockMvc.perform(put("/api/v1/Favourite").contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
	                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	    }


	    @Test
	    public void givenFavouriteFoodItemToDeleteThenShouldReturnDeletedFavourite() throws Exception {
	            when(favouriteService.deleteFavourite(favourite.getUsername(),favourite.getFoodItem())).thenReturn(favourite);
	            mockMvc.perform(delete("/api/v1/favourite?username=Raj@gmail.com&fooditem=Burger")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(favourite)))
	                    .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	    
	    
	    
	    }
	    
	    public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }


}
