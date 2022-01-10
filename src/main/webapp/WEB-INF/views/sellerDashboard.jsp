<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard - Seller</title>
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
	</div><br><br><br><br>
	
	<h2> BUYERS MOST REQUESTED </h2> 
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