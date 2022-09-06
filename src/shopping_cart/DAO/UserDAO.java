package shopping_cart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import shopping_cart.connection.JDBCUtil;
import shopping_cart.model.User;

public class UserDAO {
	private Connection connection;
	private PreparedStatement pStatement;
	public UserDAO() {
	}
	
	public User userLogin(String email,String password) throws SQLException {
    	User user = new User();
		Connection connection= JDBCUtil.getConnection();
        String sql = "select*from users where email = ? and password = ?";
        pStatement = connection.prepareStatement(sql);
        pStatement.setString(1, email);
        pStatement.setString(2, password);
        ResultSet rs = pStatement.executeQuery();
        if (rs.next()) {
        	user.setName(rs.getString("name"));
        	user.setId(rs.getInt("id"));
        	user.setEmail(rs.getString("email"));
        }
        JDBCUtil.closeConnection(connection);
		
		return user;
	}

		
}
