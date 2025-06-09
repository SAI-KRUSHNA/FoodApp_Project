package com.tap.dao;

import java.util.List;

import com.tap.models.Menu;
import com.tap.models.Restaurant;
import com.tap.models.User;

public interface MenuDAO {
	
	void addMenu(Menu menu);
	
	Menu getMenu(int menuId);
	
	void updateMenu(Menu menu);
	
	void deleteMenu(int menuId);
	
	List<Menu> getAllMenu();
	
	List<Menu> getMenuByRestaurantId(int restaurantId);

	

}
