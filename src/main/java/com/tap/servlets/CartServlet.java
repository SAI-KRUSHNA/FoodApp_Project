package com.tap.servlets;

import java.io.IOException;

import java.net.ResponseCache;

import com.mysql.cj.Session;
import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.models.Cart;
import com.tap.models.CartItem;
import com.tap.models.Menu;
import com.tap.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		Integer currentRestaurantId = (Integer) session.getAttribute("sessRestaurantId");
		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		Cart myCart=null;
		
		if(cart == null || currentRestaurantId == null || currentRestaurantId != newRestaurantId)
		{
			myCart = new Cart();
			session.setAttribute("cart", myCart);
			session.setAttribute("sessRestaurantId", newRestaurantId);
		}
		else
		{
			myCart = cart;
		}
		
		String action = req.getParameter("action");
		
		
		if("add".equals(action))
		{
			addItemToCart(req,myCart);
		}
		else if("update".equals(action))
		{
			updateItemInCart(req, myCart);
		}
		else if("remove".equals(action))
		{
			removeItemInCart(req, myCart);
		}
		
		resp.sendRedirect("cart.jsp");
		
	}

	
	
	
	private void addItemToCart(HttpServletRequest request, Cart myCart) {
		
		int itemId = Integer.parseInt(request.getParameter("menuId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		MenuDAOImpl menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenu(itemId);
		
//		if(menuItem != null)
//		{
//			CartItem item = new CartItem(menuItem.getMenuId(), menuItem.getItemName(), menuItem.getPrice(), quantity);
//			
//			myCart.addCartItem(item);
//			   
//		}
		
		if(menuItem == null) {
	        System.out.println("Menu item NOT found for id: " + itemId);
	    } else {
	        System.out.println("Adding menu item to cart: " + menuItem.getItemName() + ", quantity: " + quantity);
	        CartItem item = new CartItem(menuItem.getMenuId(), menuItem.getItemName(), menuItem.getPrice(), quantity, menuItem.getImagePath());
	        myCart.addCartItem(item);
	    }
		
		
	}
	
	

	private void updateItemInCart(HttpServletRequest request, Cart myCart) 
	{

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		myCart.updateCartItem(itemId, quantity);
	}
	
	
	
	private void removeItemInCart(HttpServletRequest request, Cart myCart)
	{
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		myCart.removeCartItem(itemId);
		
	}
	
	
}
