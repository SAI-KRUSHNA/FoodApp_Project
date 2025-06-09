package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.models.User;
import com.tap.util.DBConnection;

public class UserDAOImpl implements UserDAO {
		
	private String INSERT_USER = "INSERT INTO `user` (`username`, `password`, `email`, `phone`, `address`, `role`,`created_date`,`last_login_date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	
	private String GET_USER_BY_ID = "SELECT * FROM `user` WHERE `userid` = ?";

	
	private String UPDATE_USER = " UPDATE `user` SET `username`=? ,`password`=? ,`email`=? ,`phone`=? ,`address`=? ,`role`=? ,`created_date`=? ,`last_login_date`=?";
	
	private String  GET_ALL_USERS = "SELECT * FROM `user`";
	
	@Override
	public void addUser(User user) {

		
		try(Connection connection=DBConnection.getConnection();			
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);)
		{

			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setString(6, user.getRole());
			preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));


			int i = preparedStatement.executeUpdate();
			System.out.println(i);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserById(int userId) {

		
		User user = null;

		try(Connection connection=DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);)
			{
		
			preparedStatement.setInt(1, userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
		
			
			
			while(resultSet.next())
			{
				int userid=resultSet.getInt("userid");
				String username=resultSet.getString("username");
				String password=resultSet.getString("password");
				String email=resultSet.getString("email");
				String phone=resultSet.getString("phone");
				String address=resultSet.getString("address");
				String role=resultSet.getString("role");
				Timestamp createdDate=resultSet.getTimestamp("created_date");
				Timestamp lastLoginDate=resultSet.getTimestamp("last_login_date");

				user = new User(userid, username, password, email, phone, address, role, createdDate, lastLoginDate);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}

	
	@Override
	public void updateUser(User user) {

		try(Connection connection=DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);)
			{
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getEmail());
				preparedStatement.setString(4, user.getPhone());
				preparedStatement.setString(5, user.getAddress());
				preparedStatement.setString(6, user.getRole());
				preparedStatement.setTimestamp(7, user.getCreatedDate());
				preparedStatement.setTimestamp(8, user.getLastLoginDate());

				int i = preparedStatement.executeUpdate();
			
				System.out.println(i);
			}
			
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public List<User> getAllUser() {
		
		ArrayList<User> userList = new ArrayList<User>();

		 try(Connection connection = DBConnection.getConnection();		
				 Statement statement = connection.createStatement();)
		 {
			 
			
			 ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
					
			while(resultSet.next())
			{
				int userid=resultSet.getInt("userid");
				String username=resultSet.getString("username");
				String password=resultSet.getString("password");
				String email=resultSet.getString("email");
				String phone=resultSet.getString("phone");
				String address=resultSet.getString("address");
				String role=resultSet.getString("role");
				Timestamp createdDate=resultSet.getTimestamp("created_date");
				Timestamp lastLoginDate=resultSet.getTimestamp("last_login_date");
				
				User user = new User(userid, username, password, email, phone, address, role, createdDate, lastLoginDate);
				
				userList.add(user);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
					
		
		 Iterator cursor = userList.iterator();
		 
		 while(cursor.hasNext())
		 {
			 System.out.println(cursor.next());
		 }
		 
		return userList;
	}

	
	
    @Override
    public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        
        try(Connection connection=DBConnection.getConnection();			
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
        	preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id")); // column names must match your DB
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedDate(rs.getTimestamp("created_date"));
                user.setLastLoginDate(rs.getTimestamp("last_login_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user; // Will return null if user not found
    }

    // Other DAO methods like saveUser(), updateUser(), etc.


	

}
