package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.tap.dao.RestaurantDAO;
import com.tap.models.Restaurant;
import com.tap.models.User;
import com.tap.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

	@Override
	public void addRestaurant(Restaurant restaurant) {

		
		String INSERT_RESTAURANT = "INSERT INTO Restaurant \r\n"
				+ "(name, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imagePath)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";

		 try (Connection connection = DBConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT);)
		 {
			 
			 preparedStatement.setString(1, restaurant.getName());
			 preparedStatement.setString(2, restaurant.getCuisineType());
			 preparedStatement.setString(3, restaurant.getDeliveryTime());
			 preparedStatement.setString(4, restaurant.getAddress());
			 preparedStatement.setInt(5, restaurant.getAdminUserId());
			 preparedStatement.setDouble(6, restaurant.getRating());
			 preparedStatement.setBoolean(7, restaurant.isActive());
			 preparedStatement.setString(8, restaurant.getImagePath());
			 
			 int n = preparedStatement.executeUpdate();
			 
			 System.out.println(n);
			 
		 } 
		 catch (SQLException e) 
		 {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		String GET_RESTAURANT_BY_ID = "SELECT * FROM restaurant WHERE restaurantId = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_BY_ID);)
		{
			
			preparedStatement.setInt(1, restaurantId);
			
			 ResultSet res = preparedStatement.executeQuery();
			 
			 while (res.next()) {
				 
				    int id = res.getInt("restaurantId");
				    String name = res.getString("name");
				    String cuisineType = res.getString("cuisineType");
				    String deliveryTime = res.getString("deliveryTime");
				    String address = res.getString("address");
				    int adminUserId = res.getInt("adminUserId");
				    double rating = res.getDouble("rating");
				    boolean isActive = res.getBoolean("isActive");
				    String imagePath = res.getString("imagePath");

				    System.out.println("ID: " + id + ", Name: " + name + ", Cuisine: " + cuisineType);
				}
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		String DELETE_RESTAURANT = "DELETE FROM restaurant WHERE restaurantId = ?; ";

		 try (Connection connection = DBConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT);)
		 {
			 
			 	preparedStatement.setInt(1, restaurantId);
			 	
			 	int n = preparedStatement.executeUpdate();
			 	
			 	System.out.println(n);
			 
		 } 
		 catch (SQLException e) 
		 {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		List<Restaurant> restaurantList = new ArrayList<>();

	    String GET_ALL_RESTAURANTS = "SELECT * FROM restaurant";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RESTAURANTS);
	         ResultSet res = preparedStatement.executeQuery()) {

	        while (res.next()) {
	            Restaurant restaurant = new Restaurant();
	            restaurant.setRestaurantId(res.getInt("restaurantId"));
	            restaurant.setName(res.getString("name"));
	            restaurant.setCuisineType(res.getString("cuisineType"));
	            restaurant.setDeliveryTime(res.getString("deliveryTime"));
	            restaurant.setAddress(res.getString("address"));
	            restaurant.setAdminUserId(res.getInt("adminUserId"));
	            restaurant.setRating(res.getDouble("rating"));
	            restaurant.setActive(res.getBoolean("isActive"));
	            restaurant.setImagePath(res.getString("imagePath"));

	            restaurantList.add(restaurant);
	           
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    Iterator cursor = restaurantList.iterator();
		 
		 while(cursor.hasNext())
		 {
			 System.out.println(cursor.next());
		 }

	    return restaurantList;
	}

	
	
}
