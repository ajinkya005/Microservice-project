package com.jinks.rating.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinks.rating.entities.Rating;
import com.jinks.rating.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	//create rating
	
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
	//get all
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings() {
		return ResponseEntity.ok(ratingService.getAllRatings());
	} 
	
	//get all ratings by userId
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(ratingService.getAllRatingsOfSpecificUser(userId));
	} 
	
	//get all ratings by hotelId
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
		return ResponseEntity.ok(ratingService.getAllRatingsOfSpecificHotel(hotelId));
	} 
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Optional<Rating>> getRatingById(@PathVariable(name = "ratingId") String ratingId) {
		return ResponseEntity.ok(ratingService.getRatingById(ratingId));
	}
	
}
