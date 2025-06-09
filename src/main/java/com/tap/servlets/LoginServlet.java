package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tap.models.User;
import com.tap.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
  String CHECK_USERNAME = "SELECT * FROM users WHERE username = ?";

	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
	
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME);)
		{
			
			preparedStatement.setString(1, username);
			
			ResultSet res = preparedStatement.executeQuery();
			
			if(res.next()){
				String dbPassword= res.getString("password");
				
				if (dbPassword.equals(password)) {
					
					User user = new User();
					user.setUserId(res.getInt("userId"));
					user.setUsername(username);
					req.getSession().setAttribute("user", user);
					
					
					out.println("<h1>✅ Welcome, " + username + "</h1>");
					RequestDispatcher rd = req.getRequestDispatcher("home");
					rd.include(req, resp);
					 
	                
				} 
				else 
				{
	                out.println("<h1>❌ Hey Foodi, check your password.</h1>");
	                
	                RequestDispatcher rd = req.getRequestDispatcher("login.html");
	                rd.include(req, resp);
	            }
				
			}
			else{
				out.println("<marquee><h1>Hey Foodi Plz Compleate your Registration...!</h1></marquee>");
				
				RequestDispatcher rd = req.getRequestDispatcher("register.html");
				rd.include(req, resp);
				 
			}
			
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h1>Registration Failed, Try Again Foodi...!</h1>");

		}
		
		
	}

}


