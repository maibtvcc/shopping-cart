package shopping_cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import shopping_cart.DAO.UserDAO;
import shopping_cart.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String email = req.getParameter("login-email");
		String password = req.getParameter("login-password");
		UserDAO userDAO = new UserDAO();
		try {
			User user = userDAO.userLogin(email, password);
			System.out.println(user.toString());
			if (user.getEmail()!=null) {
				req.getSession().setAttribute("auth",user);
				resp.sendRedirect("index.jsp");
			} else {
				out.print("user login failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
