package com.jinks.user.service.services;

import java.util.List;

import com.jinks.user.service.entities.User;

public interface UserService {

	//save user
	User saveUser(User user);
	
	//get all users
	List<User> getAllUsers();
	
	//get single user
	User getUser(String id);
	
}
