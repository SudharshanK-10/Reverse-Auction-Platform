<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Reverse Auction Platform</title>
	<style><%@include file="/style.css"%></style>
</head>
<body>
	<div class="title">Reverse Auction Platform</div>
	<div class="container">
		
		<% if(request.getSession().getAttribute("InvalidLoginResponse") != null) { %>
			<div class="responsesent">Invalid Username or Password..Try Again!</div><br>
		<% request.getSession().invalidate(); } %>
		
		<% if(request.getSession().getAttribute("RegistrationStatus") != null) { %>
			<div class="responsesent"><%= request.getSession().getAttribute("RegistrationStatus") %></div><br>
		<% request.getSession().invalidate(); } %>
		
		<div class="login-container">
		Login As <br><br>
		<div class="login">
		<form action="<%= request.getContextPath() %>/buyer/login" method="post">
			<div class="buyer">
				Buyer <br><br>
				<div class = "entities">
					Email &ensp; <input type="text" name="email" required/>
				</div><br>
				<div class = "entities">
					Password &ensp; <input type="password" name="password" required/>
				</div><br>
				<input type="submit" value="Login" />
			</div>
		</form><br>
		<form action="<%= request.getContextPath() %>/seller/login" method="post">
			<div class="seller">
				Seller <br><br>
				<div class = "entities">
					Email &ensp; <input type="text" name="email" required/>
				</div><br>
				<div class = "entities">
					Password &ensp; <input type="password" name="password" required/>
				</div><br>
				<input type="submit" value="Login" />
			</div>
		</form>
		</div>
		</div><br>
		<a id="new-here" href="<%= request.getContextPath() %>/register">New here?</a>
	</div>
</body>
</html>