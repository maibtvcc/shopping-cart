package shopping_cart.servlet;

import java.awt.Checkbox;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping_cart.model.*;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		int id= Integer.parseInt(req.getParameter("id"));
		
		Cart cm = new Cart();
		cm.setId(id);
		cm.setQuantity(1);
		
		
		HttpSession session = req.getSession();
	 	ArrayList<Cart> cart_list= (ArrayList<Cart>) session.getAttribute("cart_list");
	 		 	
	 	if (cart_list==null) {
			ArrayList<Cart> cartList = new ArrayList<>();
	 		cartList.add(cm);
	 		session.setAttribute("cart_list", cartList);
		}else {
			int check=0;
			for (Cart cart : cart_list) {
				if (cart.getId()==id) {
					cart.setQuantity(cart.getQuantity()+1);
					check=1;
				}
			}
			if (check==0) {
				cart_list.add(cm);
			}
			session.setAttribute("cart_list", cart_list);
		}
	 	resp.sendRedirect("index.jsp");
	}
}
