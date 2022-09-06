<%@page import="java.util.List"%>
<%@page import="shopping_cart.DAO.OrderDAO"%>
<%@page import= "shopping_cart.model.*" %>
<%
	User auth = (User) request.getSession().getAttribute("auth");
	List<Order> orders = null;
	if(auth!=null){
		request.setAttribute("auth", auth);
		OrderDAO orderDAO = new OrderDAO();
		orders = orderDAO.userOrders(auth.getId());
	} else {
		response.sendRedirect("login.jsp");
	}
	
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Order</title>
	<%@include file = "includes/header.jsp" %>
</head>
<body>
	<%@include file = "includes/navbar.jsp" %>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%for (Order order:orders){ %>
				<tr>
					<td><%=order.getDate() %></td>
					<td><%=order.getName() %></td>
					<td><%=order.getCategory() %></td>
					<td><%=order.getO_quantity() %></td>
					<td><%=order.getPrice() %> </td>
					<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=order.getO_id() %>">Cancel Order</a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<%@include file = "includes/footer.jsp" %>
	
</body>
</html>