<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@page import= "shopping_cart.model.*" %>
<%
	User auth = null;
%>
<!DOCTYPE html>
<html>
<head>
	<title>Sign Up</title>
	<%@include file = "includes/header.jsp" %>
</head>
<body>
	<%@include file = "includes/navbar.jsp" %>
	<div class="container">
		<div class = "card w-50 mx-auto">
			<div class = "card-header text-center">Sign Up</div>
			<div class = "card-body">
				<form action = "user-sign-up" method = "post"
				oninput='password2.setCustomValidity(password2.value != password1.value ? "Passwords do not match." : "")'>
				
				<div class = "form-group">
					<label>Name</label>
					<input class = "form-control" name="name" placeholder="Enter your name" required>
				</div>
				
				<div class = "form-group">
					<label>Email address</label>
					<input type = 'email' class = "form-control" name="signup-email" placeholder="Enter your email" required>
				</div>
				
				<div class = "form-group">
					<label>Password</label>
					<input type = 'password' class = "form-control" name="password1" placeholder="******" required>
				</div>
				
				<div class = "form-group">
					<label>Confirm your password</label>
					<input type = 'password' class = "form-control" name="password2" placeholder="******" >
				</div>
				<div class=text-center>
					<button type="submit" class= "btn btn-primary">Sign Up</button>
				</div>
			
				</form>
			</div>
		</div>
	</div>
	<%@include file = "includes/footer.jsp" %>
	
</body>
</html>