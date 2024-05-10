package com.jinks.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.jinks.user.service.entities.Rating;
import com.jinks.user.service.external.services.RatingService;

@SpringBootTest
@EnableDiscoveryClient
class UserServiceApplicationTests {

	
	@Autowired
	private RatingService ratingService;
	
	@Test
	void contextLoads() {
	}
//	
//	@Test
//	void createService() {
//		
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign client").build();
//		Rating savedRating = ratingService.createRating(rating);
//		System.out.println("new rating created");
//		
//	}

}
