package test;


import java.sql.SQLException;

import shopping_cart.DAO.UserDAO;
import shopping_cart.model.User;

public class Test_UserDAO {
	public static void main(String[] args) throws SQLException {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.userLogin("abc@gmail.com","123");
		System.out.println(user.toString());
	}

	
}
