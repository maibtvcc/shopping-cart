package shopping_cart.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping_cart.DAO.*;
import shopping_cart.model.Cart;
import shopping_cart.model.Odetail;
import shopping_cart.model.Order2;
import shopping_cart.model.User;

@WebServlet("/cart-check-out")
public class CartCheckOut extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Cart> carts = (List<Cart>) req.getSession().getAttribute("cart_list");
		User user = (User) req.getSession().getAttribute("auth");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Order2DAO order2DAO=new Order2DAO();
		
		if (user==null) {resp.sendRedirect("login.jsp");}
		else {
			if (carts !=null) {
				Order2 order = new Order2();
				order.setU_id(user.getId());
				order.setDate(format.format(date));
				
				ArrayList<Odetail> odetails = new ArrayList<Odetail>();
				
				for (Cart cart : carts) {
					Odetail odetail = new Odetail();
					odetail.setP_id(cart.getId());
					odetail.setQuantity(cart.getQuantity());
					odetails.add(odetail);
				}
				
				order.setOdetails(odetails);
				order2DAO.insertOrder(order);
			}
			
			req.getSession().removeAttribute("cart_list");
			resp.sendRedirect("cart.jsp");
		}
	}
}
