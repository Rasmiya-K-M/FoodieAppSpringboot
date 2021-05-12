package com.ust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ust.Exception.RestuarantAlreadyExistsException;
import com.ust.Exception.RestuarantNotFoundException;
import com.ust.Exception.UserNotFoundException;
import com.ust.model.Favourite;
import com.ust.service.FavouriteService;
import com.ust.service.FavouriteServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")
public class FavouriteController {
	private FavouriteService favouriteService;

	@Autowired
	private FavouriteServiceImpl favouriteServiceImpl;

	public FavouriteController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	@PostMapping("/add")
	public ResponseEntity<Favourite> saveFavourite(@RequestBody Favourite favourite)
			throws RestuarantAlreadyExistsException {
		//
		Favourite savedFavourite = favouriteService.saveFavourite(favourite);
		if (savedFavourite.equals(null)) {
			throw new RestuarantAlreadyExistsException();
		}
		return new ResponseEntity<Favourite>(savedFavourite, HttpStatus.CREATED);
	}
	// done

	@GetMapping("/Favourite")
	public ResponseEntity<List<Favourite>> getAllBlogs() throws RestuarantNotFoundException {

		List<Favourite> favouriteList = favouriteService.getAllFavourite();
		if (favouriteList.isEmpty()) {
			throw new RestuarantNotFoundException();
		}
		return new ResponseEntity<List<Favourite>>(favouriteList, HttpStatus.OK);
	}

	/*
	 * This method fetches a Favourite by its id and returns the respective
	 * Favourite
	 */
	@GetMapping("Favourite/{id}")
	public ResponseEntity<Favourite> getBlogById(@PathVariable("id") int favouriteId)
			throws RestuarantNotFoundException {
		Favourite favouriteExistsOrNot = favouriteService.getFavouriteById(favouriteId);
		if (favouriteExistsOrNot == null) {
			throw new RestuarantNotFoundException();
		}
		return new ResponseEntity<Favourite>(favouriteService.getFavouriteById(favouriteId), HttpStatus.OK);
	}

	// done
	/*
	 * This method deletes a Favourite by its id and returns the deleted Favourite
	 * object
	 */
	@DeleteMapping("Favourite/{id}")
	public ResponseEntity<Favourite> getfavouriteAfterDeleting(@PathVariable("id") int favouriteId)
			throws RestuarantNotFoundException, Exception {

		Favourite favourite2 = favouriteService.deleteFavourite(favouriteId);
		if (favourite2 != null) {
			return new ResponseEntity<Favourite>(favourite2, HttpStatus.OK);
		}
		throw new RestuarantNotFoundException();
	}

	// done
	/*
	 * This method updates the Favourite content and returns the updated Favourite
	 * object
	 */
	@PutMapping("Favourite")
	public ResponseEntity<Favourite> updateFavourite(@RequestBody Favourite favourite)
			throws RestuarantNotFoundException, RestuarantAlreadyExistsException {

		Favourite favourite2 = favouriteService.updateFavourite(favourite);
		if (favourite2 != null) {
			return new ResponseEntity<>(favourite2, HttpStatus.OK);
		}

		throw new RestuarantNotFoundException();

	}

	@GetMapping("/favourite/{username}")
	public ResponseEntity<List<Favourite>> getFavouriteByUsername(@PathVariable String username)
			throws UserNotFoundException {

		return new ResponseEntity<>((favouriteService.getFavouriteByUsername(username)), HttpStatus.FOUND);
	}

	@DeleteMapping("/favourite")
	public ResponseEntity<Favourite> deleteFavourite(@RequestParam(value = "username") String username,
			@RequestParam(value = "fooditem") String foodItem) throws UserNotFoundException {

		return new ResponseEntity<Favourite>(favouriteService.deleteFavourite(username, foodItem), HttpStatus.OK);

	}
}
