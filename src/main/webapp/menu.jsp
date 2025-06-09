<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.tap.models.Menu, java.util.List" %>

    <!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Menu - The Spice Hub</title>
   <link rel="stylesheet" href="menu.css">
</head>
<body>
  <header>🍽 Menu - <%= request.getAttribute("restaurantName") %></header>

  <div class="menu-list">
    <%
    List<Menu> menus = (List<Menu>) request.getAttribute("allmenus");
    if (menus != null && !menus.isEmpty()) {
    for (Menu menu : menus) {
    %>
    
    <div class="menu-card">
    	  
	    <div class="menu-info">
	    
			  <img src="<%= menu.getImagePath() %>" alt="Menu Image" class="menu-image">	    
		      <div class="menu-details">
		      
		        <div class="item-name"><%= menu.getItemName() %></div>
		        <div class="item-desc"><%= menu.getDescription() %></div>
		        <div class="item-price">₹<%= menu.getPrice() %></div>
		
		        <form action="cart" method="post">
		        
		        	<input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
		        	<input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId() %>">
		        	<input type="hidden" name="quantity" value="1">
		        	<input type="hidden" name="action" value="add">
		        	
		        	<input type="submit" class="add-to-cart-btn" value="🛒 Add to Cart">
		        	
		        
		        </form>
		      
		      </div>  
	      </div>
	 </div>
      
      <% 
      }
    }
    else
    {
    %>
      <p style="text-align: center;">No menu items found...!</p>
    <%     
      }
    %>
  </div>
</body>

</html>
    