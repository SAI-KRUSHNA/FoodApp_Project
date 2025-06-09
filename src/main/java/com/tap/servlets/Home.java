package com.tap.servlets;

import java.io.IOException;

import java.util.List;

import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class Home extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDAOImpl rDAO=new RestaurantDAOImpl();
		
		List<Restaurant> allRestaurant = rDAO.getAllRestaurant();

		req.setAttribute("restaurants", allRestaurant);
		RequestDispatcher rd = req.getRequestDispatcher("restaurants.jsp");
		rd.forward(req, resp);
	}

}
