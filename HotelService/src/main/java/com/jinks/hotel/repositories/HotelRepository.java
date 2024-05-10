package com.jinks.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinks.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
