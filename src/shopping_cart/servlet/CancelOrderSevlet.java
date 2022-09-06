package shopping_cart.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping_cart.DAO.OrderDAO;

@WebServlet("/cancel-order")
public class CancelOrderSevlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int o_id = Integer.parseInt(req.getParameter("id"));
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.deleteOrder(o_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("orders.jsp");
	}
}
