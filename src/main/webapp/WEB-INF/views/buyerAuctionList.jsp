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
<title>Reverse Auction - Buyer</title>
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
	
	<% Item item = (Item)request.getSession().getAttribute("item"); 
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	String stringDate = formatter.format(item.getDateOfOrder());
	%>
	<div class="buyer-auction">
		<div class="left-buyer">	
			Item Name : <%= item.getItemName() %><br>
			Quantity : <%= item.getQuantity() %>
		</div>
		<div class="right-buyer">	
			Order ID : <%= item.getOrderID() %><br>
			Date Of Order : <%= stringDate %>
		</div>
	</div>
	
	<div class="bottom-buyer">
		<% if(request.getSession().getAttribute("orderover") == null) { %>
		<form action="<%= request.getContextPath() %>/buyer/lockauction" method="post">
			<button type="submit" value="lock">Lock Auction</button>
		</form>
		<%} else { %>
		<p id="ordersold">ORDER SOLD!!</p>
		<%} %>
	</div>
	
	<table id="one">
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