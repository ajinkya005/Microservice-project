package com.jinks.rating.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinks.rating.entities.Rating;
import com.jinks.rating.exception.ResourceNotFoundException;
import com.jinks.rating.repository.RatingRepository;
import com.jinks.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	

	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getAllRatingsOfSpecificUser(String userId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingsOfSpecificHotel(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public Optional<Rating> getRatingById(String id) {
		
		return ratingRepository.findById(id);
	}

}
