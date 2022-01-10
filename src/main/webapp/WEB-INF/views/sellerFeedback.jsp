<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session = "true" %>
<%@ page import = "model.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Feedback - Seller</title>
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
	<div class = "main">
		<form action="<%= request.getContextPath() %>/seller/feedback" method="post">
			<div class="main-1">
				<div class = "entities">
					Subject <input type="text" name="subject" required/>
				</div><br>
				<div class = "entities">
					Feedback  <textarea name="feedback" rows="4" cols="50" required></textarea>
				</div><br>
				<input type="submit" value="Send Feedback" />
			</div>
		</form><br>
		
		<% String res= (String) request.getSession().getAttribute("feedbacksent"); %>
		<% if(res != null) { %>
			<div class="responsesent"><%=res %></div>
		<% request.getSession().removeAttribute("feedbacksent"); } %>
	</div>
</body>
</html>