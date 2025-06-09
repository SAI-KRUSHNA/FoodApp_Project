package com.tap.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Integer, CartItem> items;
	
	public Cart() {	
		this.items = new HashMap<>();
	}
	
	
	public Map<Integer, CartItem> getItems()
	{
		return items;
	}
	
	
	
	public  void addCartItem(CartItem cartItem)
	{
		 
		int itemId = cartItem.getId();
		
		if(items.containsKey(itemId))
		{
			CartItem existingItem = items.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
		}
		else
		{
			items.put(itemId, cartItem);
		}
		
		
		
	}
	
	
	
	public  void updateCartItem(int itemId, int quantity) 
	{
		
		if(items.containsKey(itemId))
		{
			if(quantity <= 0)
			{
				items.remove(itemId);
			}
			else
			{
				items.get(itemId).setQuantity(quantity);
			}
		}
		
	}
	
	
	
	public  void removeCartItem(int cartItem) 
	{
		items.remove(cartItem);
	
	}
	
	
	
	
	
	
	
	public double getTotalPrice() {
	    double total = 0.0;
	    for (CartItem item : items.values()) {
	        total += item.getQuantity() * item.getPrice();
	    }
	    return total;
	}
	
}
