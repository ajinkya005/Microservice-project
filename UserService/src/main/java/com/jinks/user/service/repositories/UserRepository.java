package com.jinks.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinks.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
