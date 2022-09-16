<%@page import="java.util.List"%>
<%@page import="shopping_cart.DAO.Order2DAO"%>
<%@page import= "shopping_cart.model.*" %>
<%
	User auth = (User) request.getSession().getAttribute("auth");
	List<Order2> orders = null;
	if(auth!=null){
		request.setAttribute("auth", auth);
		Order2DAO order2DAO = new Order2DAO();
		orders = order2DAO.userOrders(auth.getId());
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
					<th scope="col">Order Id</th>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price/unit</th>
					<th scope="col">Quantity</th>
					<th scope="col">Total Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%for (Order2 order:orders){
					List<Odetail> odetails= order.getOdetails();
					int len = odetails.size();
					for (int i=0;i<len;i++){%>
						<tr>
						<%if (i==0){%>
							<td rowspan = "<%=len%>"><%=order.getO_id() %></td>
							<td rowspan = "<%=len%>"><%=order.getDate() %></td>
						<%}%>
						<td><%=odetails.get(i).getName() %></td>
						<td><%=odetails.get(i).getCategory() %></td>
						<td>$<%=odetails.get(i).getPrice() %> </td>
						<td><%=odetails.get(i).getQuantity() %></td>
						<td>$<%=odetails.get(i).getTotal_price() %> </td>
						<%if (i==0){%>
							<td rowspan = "<%=len%>"><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=order.getO_id() %>">Cancel Order</a></td>
						<%}%>
						</tr>
					<%}} %>
			</tbody>
		</table>
	</div>
	<%@include file = "includes/footer.jsp" %>
	
</body>
</html>