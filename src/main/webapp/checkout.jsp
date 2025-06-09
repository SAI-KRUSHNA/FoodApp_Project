<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkout</title>
<link rel="stylesheet" href="checkout.css">
</head>
<body>

	<div class="container">
		<h2>Checkout</h2>
		
		<form action="checkout">
			<label for="address">Delivery Address</label>
			<textarea id="address" name="address" required="required" placeholder="Enter Address for Delivery"></textarea>
			
			<label for="paymentMethod">Choose Payment Method</label>
			<select name="paymentMethod" id="paymentMethod" required="required">
				<option value="cod">Cash on Delivery</option>
				<option value="upi">UPI Payment</option>
				<option value="credit">Credit</option>
				<option value="debit">Debit</option>
			</select>
			
			<input type="submit" class="placeOrder" value="Place Order">
		</form>
	</div>

</body>
</html>