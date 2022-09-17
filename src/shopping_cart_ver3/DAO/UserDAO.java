package shopping_cart_ver3.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import shopping_cart_ver3.connection.HibernateUtils;
import shopping_cart_ver3.model.User;
 


public class UserDAO {
	public UserDAO() {};
	public User userLogin(String email,String password) throws SQLException {
    	User user = new User();
    	Session session = HibernateUtils.getFactory().openSession();
		String hqlString = "SELECT * FROM users WHERE email=:email AND password = :password";
		Query query = session.createQuery(hqlString);
		System.out.println(hqlString);
		query.setParameter(":email", email);
		query.setParameter(":password", password);
		
		List<User> result = query.getResultList();
		
		session.close();
		
		return user;
	}
	
}
