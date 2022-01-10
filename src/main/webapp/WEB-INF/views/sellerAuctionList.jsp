<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.*" %>
<%@ page import = "java.text.*" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reverse Auction - Seller</title>
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
	
	<% Item item = (Item)request.getSession().getAttribute("item"); 
	Buyer buyer = (Buyer)request.getSession().getAttribute("buyer");
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	String stringDate = formatter.format(item.getDateOfOrder());
	%>
	<div class="seller-auction">
		<div class="left-seller">	
			Buyer Name : <%= buyer.getName() %><br>
			Buyer Email : <%= buyer.getEmail() %><br>
			Contact No : <%= buyer.getContactNo() %><br>
			Address : <%= buyer.getAddress() %>
		</div>
		<div class="right-seller">	
			Order ID : <%= item.getOrderID() %><br>
			Date Of Order : <%= stringDate %><br>
			Item Name : <%= item.getItemName() %><br>
			Quantity : <%= item.getQuantity() %><br>
		</div>
	</div><br>
	
	<% if(request.getSession().getAttribute("orderover") == null) { %>
	<div class="bottom-seller">
		<form action="<%= request.getContextPath() %>/seller/updateofferingprice" method="post">
		 	Update Offering Price : 
		 	<input type="text" name="offeringprice" required/> &ensp;
		 	<input type="hidden" name="orderid" value="<%= item.getOrderID() %>" />
			<button type="submit" value="update">Update</button>
		</form>
	</div>
	<%} else { %>
		<p id="sold">ORDER SOLD!!</p>
	<%} %>
	
	<table id="two">
			<tr>
				<th>S.No</th>
				<th>Entreprise Name</th>
				<th>Email</th>
				<th>Contact No</th>
				<th>Offering Price</th>
			</tr>
			<% ArrayList<Auction> auctionList = (ArrayList<Auction>)request.getSession().getAttribute("auctionList"); 
			    int n = auctionList.size();
			    for(int i=0; i<n; i++) {
			%>
			<%if (i==0) { %>
				<tr bgcolor="yellow">
			<%} else { %>
				<tr>
			<%} %>
				<td><%= i+1 %></td>
				<td><%= auctionList.get(i).getEntrepriseName() %></td>
				<td><%= auctionList.get(i).getEmail() %></td>
				<td><%= auctionList.get(i).getContactNo() %></td>
				<td><%= auctionList.get(i).getOfferingPrice() %></td>
			</tr>
			<% } %>
	</table>
</body>
</html>