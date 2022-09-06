<%@page import="shopping_cart.DAO.ProductDAO"%>
<%@page import="shopping_cart.connection.JDBCUtil"%>
<%@page import= "shopping_cart.model.*" %>
<%@page import= "java.util.List" %>

<%
	User auth = (User) request.getSession().getAttribute("auth");
	if(auth!=null){
		request.setAttribute("auth", auth);
	}
	
	ProductDAO productDAO=new ProductDAO();
	List <Product> products = productDAO.getAllProduct();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome</title>
	<%@include file = "includes/header.jsp" %>
</head>
<body>
	<%@include file = "includes/navbar.jsp" %>
	<div class="container">
		<div class = "card - header my-3">All products</div>
		<div class = "row">
		<% if(!products.isEmpty()) {
				for (Product p:products){%>
					<div class = "col-md-3">
						<div class="card w-100" style="width: 18rem;">
							<img class="card-img-top" src="product_images/<%=p.getImage() %>" alt="Card image cap">
							<div class="card-body">
					  			<h5 class="card-title"><%=p.getName() %></h5>
					  			<p class="card-text">Price: $<%= p.getPrice() %><br/>
					  			Category: <%= p.getCategory() %></p>
					  			<p>
					  				<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>" role="button">Add to card</a>
					  				<a class="btn btn-primary" href="order-now?id=<%=p.getId()%>" role="button">Buy now</a>
					  				
					  				
								</p>
							</div>
						</div>
					</div>
			<%}
		}%>
		</div>
	</div>
	
	<%@include file = "includes/footer.jsp" %>
</body>
</html>