package com.ust.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Exception.RestuarantNotFoundException;
import com.ust.Exception.UserNotFoundException;
import com.ust.controller.FavouriteController;
import com.ust.model.Favourite;
import com.ust.repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {
	@Autowired
	private FavouriteRepository favouriteRepository;
	private static final Logger logger = LoggerFactory.getLogger(FavouriteController.class);

	@Override
	public Favourite saveFavourite(Favourite favourite) {

		return favouriteRepository.save(favourite);

	}

	@Override
	public List<Favourite> getAllFavourite() throws RestuarantNotFoundException {
		List<Favourite> favourite = (List<Favourite>) favouriteRepository.findAll();

		return (List<Favourite>) favouriteRepository.findAll();

	}

	@Override
	public Favourite getFavouriteById(int id) throws RestuarantNotFoundException {
		Optional<Favourite> optional = favouriteRepository.findById(id);
		favouriteRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new RestuarantNotFoundException();
	}

	@Override
	public Favourite deleteFavourite(int id) throws RestuarantNotFoundException {

		Optional<Favourite> optional = favouriteRepository.findById(id);
		if (optional.isPresent()) {
			Favourite favouriteById = favouriteRepository.findById(id).get();
			favouriteRepository.deleteById(id);
			return favouriteById;
		}

		else {
			throw new RestuarantNotFoundException();
		}

	}

	@Override
	public Favourite updateFavourite(Favourite favourite) throws RestuarantNotFoundException {
		if (favouriteRepository.existsById(favourite.getId())) {

			Favourite save = favouriteRepository.save(favourite);
			return save;

		} else
			throw new RestuarantNotFoundException();

	}

	public Favourite deleteFavourite(String username, String foodItem) throws UserNotFoundException {
		Favourite favourite = null;
		if (!favouriteRepository.existsByUsername(username) || !favouriteRepository.existsByFoodItem(foodItem)) {
			throw new UserNotFoundException();
		}

		Favourite favourite1 = favouriteRepository.findByUsernameAndFoodItem(username, foodItem);
		favourite = favouriteRepository.findByUsernameAndFoodItem(username, foodItem);
		favouriteRepository.delete(favourite1);
		logger.info("The foodItem is deleted.");
		return favourite;
	}

	public List<Favourite> getFavouriteByUsername(String username) throws UserNotFoundException {
		if (!favouriteRepository.existsByUsername(username)) {
			throw new UserNotFoundException();

		}
		logger.info("This is the list of Favourites  of User");

		List<Favourite> favourite = favouriteRepository.findByUsername(username);
		return favourite;
	}

}
