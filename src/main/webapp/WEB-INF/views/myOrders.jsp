<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>My Orders - Buyer</title>
	<style><%@include file="/style.css"%></style>
</head>
<body>
	<div class="title">Reverse Auction Platform</div>
	<br>
	<div class="user-info">
		<% Buyer buyer = (Buyer)request.getSession().getAttribute("buyer"); %>
		Logged in as : <%= buyer.getName() %><br>
		Email : <%= buyer.getEmail() %> 
	</div><br><br>
	<div class="navigation-bar">
		<a href="<%= request.getContextPath() %>/buyer/dashboard">Home</a>
		<a href="<%= request.getContextPath() %>/buyer/myorders">My Orders</a>
		<a href="<%= request.getContextPath() %>/buyer/placeorder">Place Order</a>
		<a href="<%= request.getContextPath() %>/buyer/feedback">Feedback</a>
		<a href="<%= request.getContextPath() %>/logout">Logout</a>
	</div><br><br><br><br><br>
	
		<table id="one">
			<tr>
				<th>Order ID</th>
				<th>Date Of Order</th>
				<th>Item</th>
				<th>Quantity</th>
				<th>Status</th>
			</tr>
			<% ArrayList<Item> myOrders = (ArrayList<Item>)request.getSession().getAttribute("myOrders"); 
			    int n = myOrders.size();
			    for(int i=0; i<n ;i++) {
			    
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				String stringDate = formatter.format(myOrders.get(i).getDateOfOrder());
				int orderID = myOrders.get(i).getOrderID();
			%>
			<tr>
				<td><%= orderID %></td>
				<td><%= stringDate %></td>
				<td><%= myOrders.get(i).getItemName() %></td>
				<td><%= myOrders.get(i).getQuantity() %></td>
				<td>
					<form action = "<%= request.getContextPath() %>/buyer/vieworder" method="post">
						<input type="hidden" name="orderid" value="<%=orderID %>" />
						<button type="submit">View Order</button>
					</form>
				</td>
			</tr>
			<% } %>
		</table>
</body>
</html>