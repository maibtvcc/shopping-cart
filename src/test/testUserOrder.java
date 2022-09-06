package test;

import java.sql.SQLException;
import java.util.List;

import shopping_cart.DAO.OrderDAO;
import shopping_cart.model.Order;

public class testUserOrder {
	public static void main(String[] args) throws SQLException {
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orders = orderDAO.userOrders(2);
		for (Order order : orders) {
			System.out.println("o_id: "+order.getO_id() + " p_id: "+order.getP_id()+" u_id: "+order.getU_id() + " quantity: "+order.getO_quantity() + " price: " +order.getPrice()+" cate: "+order.getCategory());
		}
	}
}
