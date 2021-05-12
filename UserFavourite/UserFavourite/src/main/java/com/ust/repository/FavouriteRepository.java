package com.ust.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.model.Favourite;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
	 Favourite findByFoodItem(String foodItem);
	    Boolean existsByFoodItem(String foodItem);
	    Boolean existsByUsername(String username);

	 List<Favourite> findByUsername(String username);
	    Favourite findByUsernameAndFoodItem(String username,String foodItem);
}
