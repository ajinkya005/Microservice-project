package com.jinks.rating.services;

import java.util.List;
import java.util.Optional;

import com.jinks.rating.entities.Rating;

public interface RatingService {

	//create
	
	Rating create(Rating rating);
	
	
	//get all ratings
	List<Rating> getAllRatings();
	
	
	//get all ratings given by specific user
	List<Rating> getAllRatingsOfSpecificUser(String userId);
	
	
	
	//get all ratings of specific hotel
	List<Rating> getAllRatingsOfSpecificHotel(String hotelId);
	
	Optional<Rating> getRatingById(String id);
	
	
}
