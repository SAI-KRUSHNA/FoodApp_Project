<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.tap.models.Restaurant, java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Foodi - Home</title>
    <link rel="icon" href="images\restaurants\Foodi_icon.png" type="image/gif" sizes="16x16">
    <link rel="stylesheet" href="restaurants.css">
   
</head>
<body>
<header>🍴 Welcome to Foodi</header>




	<div class="restaurants-container">
	<%
		List<Restaurant> allRestaurants = (List<Restaurant>) request.getAttribute("restaurants");
		for (Restaurant restaurant : allRestaurants) {
	%>
		<div class="restaurant-card">
			<a href="menuServlet?restaurantId=<%= restaurant.getRestaurantId() %>&restaurantName=<%= restaurant.getName() %>" class="restaurant-link">
		
				<div class="restaurant-info">
					<img src="<%= restaurant.getImagePath() %>" alt="Restaurant Image" class="restaurant-image">
					<div class="restaurant-details">
						<div class="restaurant-name"><%= restaurant.getName() %></div>
						<div class="rating">⭐ <%= restaurant.getRating() %></div>
						<div class="info">⏰ <%= restaurant.getDeliveryTime() %> | 📍 <%= restaurant.getAddress() %></div>
					</div>
				</div>
			</a>
		</div>
	<% } %>
	</div>
	

</body>
</html>
