package shopping_cart.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping_cart.DAO.OrderDAO;
import shopping_cart.DAO.UserDAO;
import shopping_cart.model.Cart;
import shopping_cart.model.Order;
import shopping_cart.model.User;

@WebServlet("/cart-check-out")
public class CartCheckOut extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Cart> carts = (List<Cart>) req.getSession().getAttribute("cart_list");
		User user = (User) req.getSession().getAttribute("auth");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		OrderDAO orderDAO=new OrderDAO();
		
		if (user==null) {resp.sendRedirect("login.jsp");}
		else {
			if (carts !=null) {
				for (Cart cart : carts) {
					Order order = new Order();
					order.setP_id(cart.getId());
					order.setU_id(user.getId());
					order.setO_quantity(cart.getQuantity());
					order.setDate(format.format(date));
					orderDAO.insertOrder(order);
				}
			}
			
			req.getSession().removeAttribute("cart_list");
			resp.sendRedirect("cart.jsp");
		}
	}
}
