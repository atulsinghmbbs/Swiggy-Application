package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Restaurants;

public interface RestaurantRepository extends JpaRepository<Restaurants, Integer> {

}
