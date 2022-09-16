package shopping_cart.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping_cart.DAO.Order2DAO;
import shopping_cart.model.User;

@WebServlet("/cancel-order")
public class CancelOrderSevlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int o_id = Integer.parseInt(req.getParameter("id"));
		User user = (User) req.getSession().getAttribute("auth");
		int u_id = user.getId();
		
		Order2DAO order2DAO = new Order2DAO();
		try {
			order2DAO.deleteOrder(o_id,u_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("orders2.jsp");
	}
}
