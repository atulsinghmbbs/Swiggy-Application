package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.RestaurantException;
import com.masai.model.Restaurants;
import com.masai.repository.RestaurantRepository;


@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
    private RestaurantRepository restaurantRepository;
	
	@Override
	public Restaurants addRestaurant(Restaurants restaurant) throws RestaurantException {
		return restaurantRepository.save(restaurant);
	}

	@Override
	public List<Restaurants> getAllRestaurants() throws RestaurantException {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurants getRestaurantById(int restaurantId) throws RestaurantException {
		return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant not found with ID: " + restaurantId));
	}

}
