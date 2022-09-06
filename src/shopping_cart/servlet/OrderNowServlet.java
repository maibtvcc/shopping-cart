package shopping_cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.lang.model.util.SimpleAnnotationValueVisitor14;
import javax.security.auth.message.config.AuthConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping_cart.DAO.OrderDAO;
import shopping_cart.model.*;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("auth");
		if (user == null) {
			resp.sendRedirect("login.jsp");
		}else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			int p_id = Integer.parseInt(req.getParameter("id"));
			int o_quantity = 1;
			if (req.getParameter("quantity")!=null) {
				o_quantity= Integer.parseInt(req.getParameter("quantity"));}
			
			Order order = new Order();
			order.setP_id(p_id);
			order.setU_id(user.getId());
			order.setO_quantity(o_quantity);
			order.setDate(format.format(date));
			
			OrderDAO orderDAO=new OrderDAO();
			boolean result = orderDAO.insertOrder(order);
			if (result) {
				HttpSession session = req.getSession();
				ArrayList<Cart> carts= (ArrayList<Cart>) session.getAttribute("cart_list");
				
				for (Cart cart : carts) {
					if (cart.getId()==order.getP_id()) {
						carts.remove(cart);
						break;
					}
				}
				
				resp.sendRedirect("orders.jsp");
			} else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.print("order failed. Go back <a href= 'cart.jsp'>cart</a>");
			}
			
		
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
