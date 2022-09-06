package shopping_cart.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping_cart.model.Cart;

@WebServlet("/remove-from-cart")
public class RemoveFromCart extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int id = Integer.parseInt(req.getParameter("id"));
		ArrayList<Cart> carts= (ArrayList<Cart>) session.getAttribute("cart_list");
		
		for (Cart cart : carts) {
			if (cart.getId()==id) {
				carts.remove(cart);
				break;
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
