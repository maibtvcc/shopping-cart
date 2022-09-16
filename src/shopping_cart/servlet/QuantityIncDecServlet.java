package shopping_cart.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import shopping_cart.model.Cart;

@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		int id = Integer.parseInt(req.getParameter("id"));
		ArrayList<Cart> carts = null;
		HttpSession session = req.getSession();
		carts = (ArrayList<Cart>) session.getAttribute("cart_list");
		for (Cart cart : carts) {
			if (cart.getId()==id) {
				int quantity = cart.getQuantity();
				quantity+= action.equals("inc")?1:-1;
				if (quantity ==0) {
					carts.remove(carts.indexOf(cart));
					break;
				} else {
					cart.setQuantity(quantity);
				}
			}
		}
		if (carts.isEmpty()) {
			session.removeAttribute("cart_list");
		}else {
			session.setAttribute("cart_list", carts);
		}
		resp.sendRedirect("cart.jsp");
	}
}
