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
	<title>Received Orders - Seller</title>
	<style><%@include file="/style.css"%></style>
</head>
<body>
	<div class="title">Reverse Auction Platform</div>
	<br>
	<div class="user-info">
		<% Seller seller = (Seller)request.getSession().getAttribute("seller"); %>
		Logged in as : <%= seller.getEntrepriseName() %><br>
		Email : <%= seller.getEmail() %> 
	</div><br><br>
	<div class="navigation-bar">
		<a href="<%= request.getContextPath() %>/seller/dashboard">Home</a>
		<a href="<%= request.getContextPath() %>/seller/receivedorders">Received Orders</a>
		<a href="<%= request.getContextPath() %>/seller/orderrequests">Order Requests</a>
		<a href="<%= request.getContextPath() %>/seller/feedback">Feedback</a>
		<a href="<%= request.getContextPath() %>/logout">Logout</a>
	</div><br><br><br><br><br><br>
	
		<table id="one">
			<tr>
				<th>Order ID</th>
				<th>Date Of Order</th>
				<th>Item</th>
				<th>Quantity</th>
				<th>Status</th>
			</tr>
			<% ArrayList<Item> pendingOrders = (ArrayList<Item>)request.getSession().getAttribute("pendingOrders"); 
			    int n = pendingOrders.size();
			    for(int i=0; i<n ;i++) {
			    
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				String stringDate = formatter.format(pendingOrders.get(i).getDateOfOrder());
				int orderID = pendingOrders.get(i).getOrderID();
			%>
			<tr>
				<td><%= orderID %></td>
				<td><%= stringDate %></td>
				<td><%= pendingOrders.get(i).getItemName() %></td>
				<td><%= pendingOrders.get(i).getQuantity() %></td>
				<td>
					<form action = "<%= request.getContextPath() %>/seller/vieworder" method="post">
						<input type="hidden" name="orderid" value="<%=orderID %>" />
						<button type="submit">View Order</button>
					</form>
				</td>
			</tr>
			<% } %>
		</table>
</body>
</html>