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
<title>Order Requests - Seller</title>
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
	</div><br>
	
	<% String status = (String)request.getSession().getAttribute("orderRequestStatus"); 
		if(status != null) {
	%>
	<br><br><br><br><div class="responsesent"><%= status %></div><br>
	<% 
		request.getSession().removeAttribute("orderRequestStatus");
	} %>
	
	<table id="one">
			<tr>
				<th>Order ID</th>
				<th>Date Of Order</th>
				<th>Item</th>
				<th>Quantity</th>
				<th>Response</th>
			</tr>
			<% ArrayList<Item> orderRequests = (ArrayList<Item>)request.getSession().getAttribute("orderRequests"); 
			    int n = orderRequests.size();
			    for(int i=0; i<n ;i++) {
			    
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				String stringDate = formatter.format(orderRequests.get(i).getDateOfOrder());
				int orderID = orderRequests.get(i).getOrderID();
			%>
			<tr>
				<td><%= orderID %></td>
				<td><%= stringDate %></td>
				<td><%= orderRequests.get(i).getItemName() %></td>
				<td><%= orderRequests.get(i).getQuantity() %></td>
				<td>
					<form action = "<%= request.getContextPath() %>/seller/requestresponse" method="post">
						<input type="hidden" name="orderid" value="<%=orderID %>" />
						Accept <input type="radio" onclick="document.getElementById('<%=orderID %>').disabled = false;
						" name="choice" value="accept" /> &ensp;&ensp;
						Offering price <input type="text" id="<%=orderID %>" name="offeringprice" required disabled/> <br><br>
						Reject <input type="radio" onclick="document.getElementById('<%=orderID %>').disabled = true;" 
						name="choice" value="reject" /> <br><br>
						<button type="submit">Send Response</button>
					</form>
				</td>
			</tr>
			<% } %>
		</table>
	
</body>
</html>