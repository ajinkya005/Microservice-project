package com.jinks.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.jinks.user.service.entities.Hotel;
import com.jinks.user.service.entities.Rating;
import com.jinks.user.service.entities.User;
import com.jinks.user.service.external.services.HotelService;
import com.jinks.user.service.repositories.UserRepository;
import com.jinks.user.service.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		//generate unique userid
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		
		for(User user : users) {
			
			Rating[] ratingsByUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+ user.getUserId(), Rating[].class);
			
			List<Rating> ratings = Arrays.stream(ratingsByUser).toList();
			
			List<Rating> ratingList = new ArrayList();
			
			for(Rating r : ratings) {
			//	ResponseEntity<Hotel> entity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+r.getHotelId(), Hotel.class);
				Hotel hotel = hotelService.getHotel(r.getHotelId());
				
				//logger.info("respone status code: {} ", entity.getStatusCode());
				
				r.setHotel(hotel);
				ratingList.add(r);
			}
			user.setRating(ratingList);
//			
//			List<Rating> ratingList = ratings.stream().map(rating -> {
//				
//				ResponseEntity<Hotel> entity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
//				Hotel hotel = entity.getBody();
//				
//				logger.info("respone status code: {} ", entity.getStatusCode());
//				
//				rating.setHotel(hotel);
//				return rating;
//				
//			}).collect(Collectors.toList());
//			
//			user.setRating(ratings);
			
			//user.setRating(rating);
		}
		return users;
		
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceAccessException("user with given id not found : " + id));
		
		//fetch rating of the above user from Rating service
		//sample http request - 
		// http://localhost:8083/ratings/users/f5a6a0fb-e6d3-4ae5-b8fc-71046eacc735
		
		
		
		Rating[] ratingsGivenByUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+ id, Rating[].class);
		
		logger.info("{} ", ratingsGivenByUser);
		
		List<Rating> ratings = Arrays.stream(ratingsGivenByUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
			ResponseEntity<Hotel> entity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = entity.getBody();
			
			logger.info("respone status code: {} ", entity.getStatusCode());
			
			rating.setHotel(hotel);
			return rating;
			
		}).collect(Collectors.toList());
		
		user.setRating(ratings);
	
		
		
		return user;
		
		
		
	}

}
