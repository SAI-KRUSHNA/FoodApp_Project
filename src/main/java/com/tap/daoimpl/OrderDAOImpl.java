package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tap.dao.OrderDAO;
import com.tap.models.Order;
import com.tap.models.OrderItem;
import com.tap.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public int addOrder(Order order) {
		
		int orderId = 0;
		String ADD_ORDER = "INSERT INTO orders (user_id, restaurant_id, order_date, total_amount, address, status, payment_method) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		
		try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {

	            ps.setInt(1, order.getUserId());
	            ps.setInt(2, order.getRestaurantId());
	            ps.setTimestamp(3, order.getOrderDate());
	            ps.setDouble(4, order.getTotalAmount());
	            ps.setString(5, order.getAddress());
	            ps.setString(6, order.getStatus());
	            ps.setString(7, order.getPaymentMethod());
		
		
	            int affectedRows = ps.executeUpdate();
	            
	            if (affectedRows == 0) {
	                throw new SQLException("Creating order failed, no rows affected.");
	            }
	            
	            //  Get generated order_id
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    orderId = rs.getInt(1);
	                }
	            }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		return orderId;
	}
	
//	 // Add this new method inside the same class
//    public boolean addOrderItem(OrderItem orderItem) {
//        String ADD_ORDER_ITEM = "INSERT INTO order_items (order_id, item_id, item_name, quantity, price) VALUES (?, ?, ?, ?, ?)";
//        boolean success = false;
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(ADD_ORDER_ITEM)) {
//
//            ps.setInt(1, orderItem.getOrderId());
//            ps.setInt(2, orderItem.getItemId());
//            ps.setString(3, orderItem.getItemName());
//            ps.setInt(4, orderItem.getQuantity());
//            ps.setDouble(5, orderItem.getPrice());
//
//            int affectedRows = ps.executeUpdate();
//            success = (affectedRows > 0);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return success;
//    }

}
