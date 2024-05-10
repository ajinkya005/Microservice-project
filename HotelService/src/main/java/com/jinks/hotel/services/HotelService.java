package com.jinks.hotel.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinks.hotel.entities.Hotel;

@Service
public interface HotelService {

	//create
	Hotel create(Hotel hotel);
	
	//getAll
	List<Hotel> getAll();
	
	//get Single
	Hotel get(String id);
	
}
