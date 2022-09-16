package shopping_cart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import shopping_cart.connection.JDBCUtil;
import shopping_cart.model.User;

public class UserDAO {
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
	
	public int userSignUp(String name,String email,String password) {
		int result;
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql = "select*from users where email = ?";
	        pStatement = connection.prepareStatement(sql);
	        pStatement.setString(1, email);
	        ResultSet rs = pStatement.executeQuery();
			
	        if(rs.next()) {
	        	result=1;
	        } else {
	        	sql = "INSERT INTO users (name,email,password) VALUES(?,?,?)";
		        pStatement = connection.prepareStatement(sql);
		        pStatement.setString(1, name);
		        pStatement.setString(2, email);
		        pStatement.setString(3, password);
		        pStatement.executeUpdate();
				result=0;
			}
	        JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			result=-1;
		}
		return result;
	}

		
}
