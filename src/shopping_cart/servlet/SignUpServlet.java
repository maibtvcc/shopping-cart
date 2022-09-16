package shopping_cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping_cart.DAO.UserDAO;

@WebServlet("/user-sign-up")
public class SignUpServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String email = req.getParameter("signup-email");
		String password = req.getParameter("password1");
		
		UserDAO userDAO = new UserDAO();
		int result = userDAO.userSignUp(name, email, password);
		System.out.println(result);
		if (result==1) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Email has been used. Try another email');");
			out.println("location='sign_up.jsp';");
			out.println("</script>");
		} else if (result==0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Sign up success');");
			out.println("location='login.jsp';");
			out.println("</script>");
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Sign up failed.Try again');");
			out.println("location='sign_up.jsp';");
			out.println("</script>");
		}
	}
}
