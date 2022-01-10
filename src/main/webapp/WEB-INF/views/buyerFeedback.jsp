<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session = "true" %>
<%@ page import = "model.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Feedback - Buyer</title>
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
		<form action="<%= request.getContextPath() %>/buyer/feedback" method="post">
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