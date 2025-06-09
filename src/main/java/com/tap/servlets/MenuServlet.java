package com.tap.servlets;
import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.MenuDAOImpl;
import com.tap.models.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Step 1: Get restaurantId & name from request param
        String restaurantIdStr = req.getParameter("restaurantId");
        String restaurantName = req.getParameter("restaurantName");

        // Step 2: If parameters present, save to session
        if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
            try {
                int restaurantId = Integer.parseInt(restaurantIdStr);
                session.setAttribute("restaurantId", restaurantId);
                session.setAttribute("restaurantName", restaurantName);
            } catch (NumberFormatException e) {
                // invalid id, redirect back to restaurants or handle error
                resp.sendRedirect("restaurants.jsp");
                return;
            }
        }

        // Step 3: Get restaurantId from session (for cases where params not sent on refresh)
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        if (restaurantId == null) {
            // No restaurant selected
            resp.sendRedirect("restaurants.jsp");
            return;
        }

        // Get restaurantName from session if null in request
        if (restaurantName == null) {
            restaurantName = (String) session.getAttribute("restaurantName");
        }

        // Step 4: Load menu from DB using restaurantId
        MenuDAOImpl menuDAO = new MenuDAOImpl();
        List<Menu> allMenuByRestaurantId = menuDAO.getMenuByRestaurantId(restaurantId);

        req.setAttribute("allmenus", allMenuByRestaurantId);
        req.setAttribute("restaurantName", restaurantName);

        // Step 5: Forward to menu.jsp
        RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
        rd.forward(req, resp);
    }
}





