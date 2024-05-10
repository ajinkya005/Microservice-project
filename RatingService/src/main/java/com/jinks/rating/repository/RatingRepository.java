package com.jinks.rating.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jinks.rating.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

	//custom finders
	
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
	
	
}
