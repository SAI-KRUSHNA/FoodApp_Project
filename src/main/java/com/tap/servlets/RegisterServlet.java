package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tap.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurantServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String INSERT_USER = 
        "INSERT INTO users(name, email, phone_number, username, password) VALUES (?, ?, ?, ?, ?)";
    
    private static final String CHECK_EMAIL = 
        "SELECT email FROM users WHERE email = ?";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection connection = DBConnection.getConnection()) {

            // Step 1: Check if email already exists
            try (PreparedStatement checkStmt = connection.prepareStatement(CHECK_EMAIL)) {
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    out.println("<h3 style='color:red;text-align: center;'>Email already registered. Try with a different one.</h3>");
                    RequestDispatcher rd = req.getRequestDispatcher("register.html");
                    rd.include(req, resp);
                    return;
                }
            }

            // Step 2: Insert new user
            try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_USER)) {
                insertStmt.setString(1, name);
                insertStmt.setString(2, email);
                insertStmt.setString(3, phoneNumber);
                insertStmt.setString(4, username);
                insertStmt.setString(5, password);

                int rowsInserted = insertStmt.executeUpdate();

                if (rowsInserted > 0) {
                    out.println("<h3 style='color:green; text-align: center;' >Registration Successful! Welcome, " + name + ".</h3>");
                    RequestDispatcher rd = req.getRequestDispatcher("login.html");
                    rd.include(req, resp);
                } else {
                    out.println("<h3 style='color:red;'>Registration Failed. Please try again.</h3>");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Server error occurred. Please try again later.</h3>");
        }
    }
}
