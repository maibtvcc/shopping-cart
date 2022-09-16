package shopping_cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.security.auth.message.config.AuthConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping_cart.DAO.*;
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
			int quantity = 1;
			if (req.getParameter("quantity")!=null) {
				quantity= Integer.parseInt(req.getParameter("quantity"));}
			
			Order2 order = new Order2();
			order.setU_id(user.getId());
			order.setDate(format.format(date));
			
			ArrayList<Odetail> odetails = new ArrayList<Odetail>();
			Odetail odetail = new Odetail();
			odetail.setP_id(p_id);
			odetail.setQuantity(quantity);
			odetails.add(odetail);
			order.setOdetails(odetails);
			
			Order2DAO order2DAO=new Order2DAO();
			boolean result = order2DAO.insertOrder(order);
			
			
			if (result&req.getParameter("quantity")!=null) {
				HttpSession session = req.getSession();
				ArrayList<Cart> carts= (ArrayList<Cart>) session.getAttribute("cart_list");
				for (Cart cart : carts) {
					if (cart.getId()==p_id) {
						carts.remove(cart);
						break;
					}
				}
				
				resp.sendRedirect("orders2.jsp");
			} else if (result) {
				resp.sendRedirect("orders2.jsp");
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
