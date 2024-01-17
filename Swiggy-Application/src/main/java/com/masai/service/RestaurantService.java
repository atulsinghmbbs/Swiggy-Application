package com.masai.service;

import java.util.List;

import com.masai.exception.RestaurantException;
import com.masai.model.Restaurants;

public interface RestaurantService {

	public Restaurants addRestaurant(Restaurants restaurant) throws RestaurantException;
	
	public List<Restaurants> getAllRestaurants()throws RestaurantException;
	
	public Restaurants getRestaurantById(int restaurantId) throws RestaurantException;
}
