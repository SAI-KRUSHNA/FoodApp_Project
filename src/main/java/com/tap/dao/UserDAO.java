package com.tap.dao;

import java.util.List;

import com.tap.models.User;

public interface UserDAO {
	
	void addUser(User user);
	
	User getUserById(int userId);
	
	void updateUser(User user);
	
	void deleteUser(int userId);
	
	List<User> getAllUser();
	
	User getUserByEmail(String email);
	

}
