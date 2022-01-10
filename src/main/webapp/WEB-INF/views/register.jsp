<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New user - Register</title>
<style><%@include file="/style.css"%></style>
</head>

<body>
	<div class="title">Reverse Auction Platform</div>
	<form id = "register-form" action = "<%= request.getContextPath() %>/register" method="post">
		<div id="choice">
			<input type="radio" onclick="document.getElementsByClassName('b')[0].disabled = false;
				document.getElementsByClassName('b')[1].disabled = false;
				document.getElementsByClassName('b')[2].disabled = false;
				document.getElementsByClassName('b')[3].disabled = false;
				document.getElementsByClassName('b')[4].disabled = false;
				
				document.getElementsByClassName('s')[0].disabled = true;
				document.getElementsByClassName('s')[1].disabled = true;
				document.getElementsByClassName('s')[2].disabled = true;
				document.getElementsByClassName('s')[3].disabled = true;
				document.getElementsByClassName('s')[4].disabled = true;
				document.getElementsByClassName('s')[5].disabled = true;
				
			" name="user" value="buyer" />Buyer&ensp;&ensp;
			
			<input type="radio" onclick="document.getElementsByClassName('b')[0].disabled = true;
				document.getElementsByClassName('b')[1].disabled = true;
				document.getElementsByClassName('b')[2].disabled = true;
				document.getElementsByClassName('b')[3].disabled = true;
				document.getElementsByClassName('b')[4].disabled = true;
				
				document.getElementsByClassName('s')[0].disabled = false;
				document.getElementsByClassName('s')[1].disabled = false;
				document.getElementsByClassName('s')[2].disabled = false;
				document.getElementsByClassName('s')[3].disabled = false;
				document.getElementsByClassName('s')[4].disabled = false;
				document.getElementsByClassName('s')[5].disabled = false;
				
			" name="user" value="seller" />Seller
		</div><br>
		<div id="choice">
			Name &ensp; <input class="b" type="text" name="buyerName" required disabled/><br>
		</div><br>
		<div id="choice">
			Email &ensp;<input class="b" type="text" name="buyerEmail" required disabled/><br>
		</div><br>
		<div id="choice">
			Password &ensp;<input class="b" type="password" name="buyerPassword" required disabled/><br>
		</div><br>
		<div id="choice">
			Address &ensp;<textarea class="b" form="register-form" name="buyerAddress" rows="4" cols="50" disabled></textarea>
		</div><br>
		<div id="choice">
			Contact No  &ensp;<input class="b" type="text" name="buyerContactNo" required disabled/><br>
		</div><br><br>
		<div id="choice">
			Entreprise Name  &ensp;<input class="s" type="text" name="sellerEntrepriseName" required disabled/><br>
		</div><br>
		<div id="choice">
			Entreprise Email  &ensp;<input class="s" type="text" name="sellerEmail" required disabled/><br>
		</div><br>
		<div id="choice">
			Password &ensp;<input class="s" type="password" name="sellerPassword" required disabled/><br>
		</div><br>
		<div id="choice">
			Entreprise Owner Name &ensp;<input class="s" type="text" name="sellerOwnerName" required disabled/><br>
		</div><br>
		<div id="choice">
			Address  &ensp;<textarea class="s" form="register-form" name="sellerAddress" rows="4" cols="50" disabled></textarea>
		</div><br>
		<div id="choice">
			Contact No  &ensp;<input class="s" type="text" name="sellerContactNo" required disabled/><br>
		</div><br>
		<input id="reg" type="submit" value="Register" /><br><br>
	</form>
</body>

</html>