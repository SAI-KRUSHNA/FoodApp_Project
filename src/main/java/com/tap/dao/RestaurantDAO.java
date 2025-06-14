package com.tap.dao;

import java.util.List;

import com.tap.models.Restaurant;
import com.tap.models.User;

public interface RestaurantDAO {
	
	void addRestaurant(Restaurant restaurant);
	
	Restaurant  getRestaurant(int restaurantId);
	
	void updateRestaurant(Restaurant restaurant);
	
	void deleteRestaurant(int restaurantId);
	
	List<Restaurant> getAllRestaurant();

}
