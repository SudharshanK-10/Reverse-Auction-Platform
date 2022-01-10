<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard - Buyer</title>
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
	</div><br><br><br><br>
	<h2> TOP SELLING </h2> 
	<div class="home">
		<% ArrayList<String>topSellers = (ArrayList<String>)request.getSession().getAttribute("topSellers");
		int n = topSellers.size() >= 8 ? 8 : topSellers.size();
		
		for(int i=0;i<8;i+=2) {
		%>
			<div>
				<p id="big"><%= topSellers.get(i) %></p>
				<p id="small">Requested by - <%= topSellers.get(i+1) %></p>
			</div>
		<%} %>
	</div>
</body>
</html>