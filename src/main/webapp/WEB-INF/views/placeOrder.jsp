<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session = "true" %>
<%@ page import = "model.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Place Order - Buyer</title>
	<style><%@include file="/style.css"%></style>
</head>
<body>
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
	</div><br><br><br><br>
	
	<div class = "main">
		<form action="<%= request.getContextPath() %>/buyer/placeorder" method="post">
			<div class="main-1">
				<div class = "entities">
					Item Name  <input type="text" name="itemname" required/>
				</div><br>
				<div class = "entities">
					Quantity  <input type="number" name="quantity" min="1" max="100" step="1" value="1" required/>
				</div><br>
				<input type="submit" value="Place Order" />
			</div>
		</form><br>
		
		<% String res= (String) request.getSession().getAttribute("orderPlacementResponse"); %>
		<% if(res != null) { %>
			<div class="responsesent"><%=res %></div>
		<% request.getSession().removeAttribute("orderPlacementResponse"); } %>
	</div>
</body>
</body>
</html>