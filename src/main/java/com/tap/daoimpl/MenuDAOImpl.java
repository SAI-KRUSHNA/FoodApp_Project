package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.models.Menu;
import com.tap.models.Restaurant;
import com.tap.models.User;
import com.tap.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	@Override
	public void addMenu(Menu menu) {
		
		String INSERT_MENU = "INSERT INTO `menu`(`restaurantId`,`itemName`,`description`,`price`,`isAvailable`,`imagePath`) VALUES(?,?,?,?,?,?)";
		
	   
	   try (Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENU);)

	   {
		   
		   preparedStatement.setInt(1, menu.getRestaurantId());
		   preparedStatement.setString(2, menu.getItemName());
		   preparedStatement.setString(3, menu.getDescription());
		   preparedStatement.setFloat(4, menu.getPrice());
		   preparedStatement.setBoolean(5, menu.isAvailable());
		   preparedStatement.setString(6, menu.getImagePath());
		   
		   int n = preparedStatement.executeUpdate();
		   
		   System.out.println(n);
		     
	   }
	   catch (SQLException e) {
		   e.printStackTrace();
	   }	
		
	}
	

//	@Override
//	public Menu getMenu(int menuId) {
//		
//		String GET_MENU_BY_ID = "SELECT * FROM menu WHERE menuId = ?";
//		
//
//		
//		try (Connection connection = DBConnection.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(GET_MENU_BY_ID);)
//		{
//			 preparedStatement.setInt(1, menuId);
//			  ResultSet res = preparedStatement.executeQuery();
//			  
//			  while(res.next())
//			  {
//
//				int restaurantId = res.getInt("restaurantId");
//				String itemName = res.getString("itemName");
//				String description = res.getString("description");
//				double price = res.getFloat("price");
//				boolean isAvailable = res.getBoolean("isAvailable");
//				String imagePath = res.getString("imagePath");
//				
//
//				
//				System.out.println("itemName: "+itemName+", description: "+description+", price: "+price+", isAvailable: "+isAvailable);
//			  
//			  }	
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
	
	
	@Override
	public Menu getMenu(int menuId) {
	    String GET_MENU_BY_ID = "SELECT * FROM menu WHERE menuId = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_MENU_BY_ID);) {

	        preparedStatement.setInt(1, menuId);
	        ResultSet res = preparedStatement.executeQuery();

	        if (res.next()) {  // Use if instead of while because menuId should be unique
	            Menu menu = new Menu();

	            menu.setMenuId(menuId);
	            menu.setRestaurantId(res.getInt("restaurantId"));
	            menu.setItemName(res.getString("itemName"));
	            menu.setDescription(res.getString("description"));
	            menu.setPrice(res.getFloat("price"));
	            menu.setAvailable(res.getBoolean("isAvailable"));
	            menu.setImagePath(res.getString("imagePath"));

	            System.out.println("itemName: " + menu.getItemName() + ", description: " + menu.getDescription() + ", price: " + menu.getPrice() + ", isAvailable: " + menu.isAvailable());

	            return menu;  // **return the found menu!**
	        } else {
	            System.out.println("Menu item NOT found for id: " + menuId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;  // Return null if no menu found or exception occurs
	}


	
	@Override
	public void updateMenu(Menu menu) {

		String UPDATE_MENU = "UPDATE `menu` SET `menuId`=?, `restaurantId`=?, `itemName`=?, `description`=?, `price`=?, `isAvailable`=?, `imagePath`=? ";
		
		try (Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_MENU);)
		{

			preparedStatement.setInt(1, menu.getMenuId());
			preparedStatement.setInt(2, menu.getRestaurantId());
			preparedStatement.setString(3, menu.getItemName());
			preparedStatement.setString(4, menu.getDescription());
			preparedStatement.setFloat(5, menu.getPrice());
			preparedStatement.setBoolean(6, menu.isAvailable());
			preparedStatement.setString(7, menu.getImagePath());
			
			int n = preparedStatement.executeUpdate();
			
			System.out.println(n);
		
		
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void deleteMenu(int menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Menu> getAllMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {
		
		List<Menu> menuList = new ArrayList<>();
        String GET_MENU_BY_RESTAURANT_ID = "SELECT * FROM Menu WHERE restaurantId = ?";
        
        try (Connection connection = DBConnection.getConnection();
        		PreparedStatement preparedStatement= connection.prepareStatement(GET_MENU_BY_RESTAURANT_ID);)
        {
        		
        	preparedStatement.setInt(1, restaurantId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) 
            {
                Menu menu = new Menu();
                menu.setMenuId(rs.getInt("menuId"));
                menu.setRestaurantId(rs.getInt("restaurantId"));
                menu.setItemName(rs.getString("itemName"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getFloat("price"));
                menu.setAvailable(rs.getBoolean("isAvailable"));
                menu.setImagePath(rs.getString("imagePath"));

                menuList.add(menu);
        	
        
            } 
        }
        catch (SQLException e) {
					e.printStackTrace();
				}
        
        return menuList;
	}



}
