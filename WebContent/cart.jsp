<%@page import="shopping_cart.DAO.ProductDAO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import= "shopping_cart.model.*" %>
<%
	User auth = (User) request.getSession().getAttribute("auth");
	if(auth!=null){
		request.setAttribute("auth", auth);
	}
	
	ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
	List<Cart> carts = new ArrayList<Cart>();
	if (cart_list !=null){
		ProductDAO productDAO = new ProductDAO();
		carts = productDAO.getCartProducts(cart_list);
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Shopping cart</title>
	<%@include file = "includes/header.jsp" %>
	<style type="text/css">
		.table tbody td{
		vertical-align: middle;
		}
		.btn-incre, .btn-decre{
		box-shadow: none;
		font-size: 25px;
		}
	</style>
</head>
<body>
	<%@include file = "includes/navbar.jsp" %>
	
	<% double total = 0;
	for (Cart c: carts){
		total += c.getPrice();
	}%>
	
	
	<div class="container my-3">
		<div class="d-flex py-3"><h3>Total Price: $ <%=total %> </h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%for (Cart c: carts) {%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=c.getPrice() %>$</td>
					<td>
						<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i class="fa fa-minus-square"></i></a>
								<input type="text" name="quantity" class="form-control"  value=<%=c.getQuantity()%> readonly> 
								<a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i class="fa fa-plus-square"></i></a> 							
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="remove-from-cart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
	
	<%@include file = "includes/footer.jsp" %>
	
</body>
</html>