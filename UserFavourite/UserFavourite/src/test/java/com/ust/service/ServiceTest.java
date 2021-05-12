package com.ust.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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


import com.ust.Exception.RestuarantAlreadyExistsException;
import com.ust.Exception.RestuarantNotFoundException;
import com.ust.model.Favourite;
import com.ust.repository.FavouriteRepository;
@ExtendWith(MockitoExtension.class)
class ServiceTest {

	@Mock
    private FavouriteRepository favouriteRepository;

    @InjectMocks
    private FavouriteServiceImpl favouriteService;
    private Favourite favourite;
    private List<Favourite> favouriteList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        favourite= new Favourite(1, "1", "Restuarant", "SampleforTesting", "erf", 240, "raj");
        optional = Optional.of(favourite);
    }

    @AfterEach
    public void tearDown() {
    	favourite = null;
    }

    @Test
    void givenFavouriteToSaveThenShouldReturnSavedFavourite() throws RestuarantAlreadyExistsException {
    	   when(favouriteRepository.save(any())).thenReturn(favourite);
           assertEquals(favourite, favouriteService.saveFavourite(favourite));
           verify(favouriteRepository, times(1)).save(any());
       }


//    @Test
//    public void givenFavouriteToSaveThenShouldNotReturnSavedFavourite() throws RestuarantAlreadyExistsException {
//        when(favouriteRepository.save(favourite)).thenThrow(new RestuarantAlreadyExistsException());
//        Assertions.assertThrows(RestuarantAlreadyExistsException.class, () ->
//        favouriteService.saveFavourite(favourite));
//        verify(favouriteRepository, times(1)).save(favourite);
//    }

   // @Test
//    void givenGetAllfavouritesThenShouldReturnListOfAllfavourites() throws RestuarantNotFoundException, Exception {
//    	favouriteRepository.save(favourite);
//        //stubbing the mock to return specific data
//        when(favouriteRepository.findAll()).thenReturn(favouriteList);
//        List<Favourite> favouriteList1 = favouriteService.getAllFavourite();
//        assertEquals(favouriteList, favouriteList1);
//        verify(favouriteRepository, times(1)).save(favourite);
//        verify(favouriteRepository, times(1)).findAll();
//    }

    @Test
    public void givenfavouriteIdThenShouldReturnRespectivefavourite() throws RestuarantNotFoundException {
        when(favouriteRepository.findById(anyInt())).thenReturn(Optional.of(favourite));
        Favourite retrievedFavourite = favouriteService.getFavouriteById(favourite.getId());
        verify(favouriteRepository, times(2)).findById(anyInt());

    }

    @Test
    void givenfavouriteIdToDeleteThenShouldDeleteRespectivefavourite() throws RestuarantNotFoundException {
        when(favouriteRepository.findById(favourite.getId())).thenReturn(optional);
        Favourite deletedfavourite = favouriteService.deleteFavourite(1);
        assertEquals(1, deletedfavourite.getId());

        verify(favouriteRepository, times(2)).findById(favourite.getId());
        verify(favouriteRepository, times(1)).deleteById(favourite.getId());
    }

//    @Test
//    void givenfavouriteIdToDeleteThenShouldNotReturnDeletedfavourite() throws RestuarantNotFoundException {
//        when(favouriteRepository.findById(favourite.getId())).thenThrow(RestuarantNotFoundException.class);
//        Assertions.assertThrows(RestuarantNotFoundException.class, () ->
//                favouriteService.deleteFavourite(1));
//        verify(favouriteRepository, times(1)).findById(favourite.getId());
//    }

    @Test
    public void givenfavouriteToUpdateThenShouldReturnUpdatedfavourite() throws RestuarantNotFoundException {
        when(favouriteRepository.existsById(favourite.getId())).thenReturn(true);
        when(favouriteRepository.save(favourite)).thenReturn(favourite);
        favourite.setFoodItem("SamplefavouriteforTesting");
        Favourite favourite1 = favouriteService.updateFavourite(favourite);
        assertEquals(favourite1.getFoodItem(), "SamplefavouriteforTesting");
        verify(favouriteRepository, times(1)).save(favourite);
        verify(favouriteRepository, times(1)).existsById(favourite.getId());
    }

    @Test
    public void givenfavouriteToUpdateThenShouldNotReturnUpdatedfavourite() throws RestuarantNotFoundException {
        when(favouriteRepository.existsById(favourite.getId())).thenReturn(false);
        Assertions.assertThrows(RestuarantNotFoundException.class, () ->
                favouriteService.updateFavourite(favourite));
        verify(favouriteRepository, times(1)).existsById(favourite.getId());
    }

}
