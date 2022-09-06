<%@page import= "shopping_cart.model.*" %>
<%
	User auth = (User) request.getSession().getAttribute("auth");
	if(auth!=null){
		request.setAttribute("auth", auth);
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<%@include file = "includes/header.jsp" %>
</head>
<body>
	<%@include file = "includes/navbar.jsp" %>
	<div class="container">
		<div class = "card w-50 mx-auto">
			<div class = "card-header text-center">User Login</div>
			<div class = "card-body">
				<form action = "user-login" method = "post">
				
				<div class = "form-group">
					<label>Email address</label>
					<input type = 'email' class = "form-control" name="login-email" placeholder="Enter your email" required>
				</div>
				
				<div class = "form-group">
					<label>Password</label>
					<input type = 'password' class = "form-control" name="login-password" placeholder="******" required>
				</div>
				
				<div class=text-center>
					<button type="submit" class= "btn btn-primary">Login</button>
				</div>
			
				</form>
			</div>
		</div>
	</div>
	<%@include file = "includes/footer.jsp" %>
	
</body>
</html>