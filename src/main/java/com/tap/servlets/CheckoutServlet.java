package com.tap.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.daoimpl.OrderDAOImpl;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.dao.OrderItemDAO;
import com.tap.models.Cart;
import com.tap.models.CartItem;
import com.tap.models.Order;
import com.tap.models.OrderItem;
import com.tap.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        // Retrieve cart and user from session
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        // Check if user is logged in
        if (user == null) {
            // User not logged in - redirect to login page
            resp.sendRedirect("login.html");
            return;
        }

        // Check if cart exists and has items
        if (cart == null || cart.getItems().isEmpty()) {
            // Cart is empty or null - redirect to cart page
            resp.sendRedirect("cart.jsp");
            return;
        }

        


        if (cart != null && user != null && !cart.getItems().isEmpty()) {

            // Step 1: Get form data
            String paymentMethod = req.getParameter("paymentMethod");
            String address = req.getParameter("address");

            // Step 2: Prepare order object
            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setRestaurantId((int) session.getAttribute("restaurantId"));
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setPaymentMethod(paymentMethod);
            order.setAddress(address);
            order.setStatus("Pending");

            // Step 3: Calculate total amount
            double totalAmount = 0;
            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }
            order.setTotalAmount(totalAmount);

            // Step 4: Store order in DB
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            int orderId = orderDAO.addOrder(order);

            if (orderId > 0) {
                // Step 5: Store each order item
            	OrderItemDAO itemDAO = new OrderItemDAOImpl();
                for (CartItem cartItem : cart.getItems().values()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setRestaurantId((int) session.getAttribute("restaurantId"));  // Set restaurantId here
                    orderItem.setItemId(cartItem.getId());
                    orderItem.setItemName(cartItem.getName());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getPrice());

                    itemDAO.addOrderItem(orderItem);
                }

                
                
                // Step 6: Clear cart and redirect
                session.removeAttribute("cart");
                resp.sendRedirect("order-success.jsp");
            } else {
                resp.sendRedirect("order-failure.jsp");
            }

        } else {
            resp.sendRedirect("cart.jsp");
        }
    }
}
