package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.tap.dao.OrderItemDAO;
import com.tap.models.OrderItem;
import com.tap.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	@Override
	public boolean addOrderItem(OrderItem item) {
		String query = "INSERT INTO order_items (order_id, restaurant_id, item_id, item_name, quantity, price) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(query)) {

		    ps.setInt(1, item.getOrderId());
		    ps.setInt(2, item.getRestaurantId());
		    ps.setInt(3, item.getItemId());
		    ps.setString(4, item.getItemName());
		    ps.setInt(5, item.getQuantity());
		    ps.setDouble(6, item.getPrice());

		    return ps.executeUpdate() > 0;
		} catch (Exception e) {
		    e.printStackTrace();
		}
        return false;
    
	}

}
