package com.jinks.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jinks.user.service.entities.Rating;


/*
 * 
 * Feign client is one of the  alternatives (other one is RestTemplate) through with we can
 * call various services. It is a declarative http web client developed by Netflix
 * 
 * It is used to create Http request, just like we used to create with the help of RestTemplate
 * 
 * If you want to use feign, create an interface and annotate it with @FeignClient
 * 
 * 
 */

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

	//get
	
	
	//post

	@PostMapping("/ratings")
	public Rating createRating(Rating rating); 
	
	
	//put
	
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
	
	
	@DeleteMapping("/ratings/{ratingId}")
	public Rating deleteRating(@PathVariable("ratingId") String ratingId);
	
}
