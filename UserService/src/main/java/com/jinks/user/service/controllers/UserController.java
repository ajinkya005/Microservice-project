package com.jinks.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinks.user.service.entities.User;
import com.jinks.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	//create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
	}
	
	int retryCount=1;
	
	//single user get
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name="ratingHotelServiceRetry", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		
		logger.info("Retry count: {}" + retryCount);
		retryCount++;
		
		User user1 = userService.getUser(userId);
		return ResponseEntity.ok(user1);
		
	}
	
	//create a fallback method to execute circuitbreaker mechanism
	
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception e) {
		logger.info("fallback is executed since one of the service is down " + e.getMessage());
		
		User user = User.builder()
				.email("dummy@gmail.com")
				.name("dummyName")
				.about("dummy user created since one of the service is down")
				.userId("234564")
				.build();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	
	//all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}
	
	
}
