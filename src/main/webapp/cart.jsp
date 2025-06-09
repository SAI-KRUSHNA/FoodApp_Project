<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import= "com.tap.models.Cart, com.tap.models.CartItem" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet" href="cart.css">
</head>
<body>

	<h1>Your Cart</h1>
	<div class="cart-container">
	
		<%
		
		Cart cart = (Cart) session.getAttribute("cart");
		Integer restaurantId = (Integer) session.getAttribute("sessRestaurantId");
		
		if(cart != null && !cart.getItems().isEmpty())
		{
			for(CartItem item : cart.getItems().values())
			{

		%>
		<div class="cart-item">
				
				<div class="item-img-name">
					<img src="<%= item.getImagePath() %>" alt="Item Image" class="item-image"> <br>
					<p><%= item.getName() %></p>
				</div>
				
			<div class="cart-item-details">
				
				<p>Quantity: <%= item.getQuantity() %></p>
				<p>Price: ₹<%= item.getPrice() %></p>
				<p>Total: ₹<%= item.getPrice() * item.getQuantity() %></p>
				

				
				
				<div class="quantity-controls">
					<form action="cart" method="post" style="display: inline;">
						<input type="hidden" name="itemId" value="<%= item.getId() %>">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
						<input type="hidden" name="restaurantId" value="<%= restaurantId %>">
						<button class="quantity-btn">+</button>
					</form>
					
					<p><%= item.getQuantity() %>
					
					<form action="cart" method="post" style="display: inline;">
						<input type="hidden" name="itemId" value="<%= item.getId() %>">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
						<input type="hidden" name="restaurantId" value="<%= restaurantId %>">
						<button class="quantity-btn" <% if(item.getQuantity() == 1){ %>disabled<% } %>>-</button>
					</form>
					
					<form action="cart" method="post" style="display: inline;">
					    <input type="hidden" name="itemId" value="<%= item.getId() %>">
					    <input type="hidden" name="action" value="remove">
					    <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
					    <button class="quantity-btn remove-btn" type="submit">Remove</button>
					</form>
					
					
					
				</div>
				
		
			</div>
		</div>
		

		
		<% 
			}
		%>
		
		
		<%
		double totalAmount = 0.0;  // start with 0
		
		for (CartItem item : cart.getItems().values()) {
		    double itemTotal = item.getPrice() * item.getQuantity(); // price * quantity
		    totalAmount += itemTotal;  // add item total to running total
		}
		%>
		
		<div class="add-more-items" style="text-align: center;">
    		<a href="menuServlet?restaurantId=<%= restaurantId %>&restaurantName=YourRestaurant">Add More Items</a>    
		</div>

		<div class="total-checkout-wrapper">
		    <h2>Total Amount: ₹<%= totalAmount %></h2>
		    <form action="checkout.jsp" method="post">
		        <input type="submit" class="checkout-btn" value="Proceed to Checkout">
		    </form>
		</div>

		
		

		
		<%	
		}
		
		else
		{
			
		%>
		<p style="text-align: center">Your Cart is Empty...!</p>
		<%
		}
		%>
		
		
		
	</div>



</body>
</html>