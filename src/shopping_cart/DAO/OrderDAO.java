package shopping_cart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import shopping_cart.connection.JDBCUtil;
import shopping_cart.model.Order;
import shopping_cart.model.Product;

public class OrderDAO {

	public OrderDAO() {	}
	
	public boolean insertOrder(Order order) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql = "INSERT INTO orders (p_id,u_id,o_quantity,o_date) VALUES (?,?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, order.getP_id());
			pStatement.setInt(2, order.getU_id());
			pStatement.setInt(3, order.getO_quantity());
			pStatement.setString(4, order.getDate());
			pStatement.executeUpdate();
			JDBCUtil.closeConnection(connection);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}
	
	public List<Order> userOrders(int u_id) throws SQLException {
		ArrayList<Order> orders = new ArrayList<Order>();
		Connection connection = JDBCUtil.getConnection();
		String sql = "SELECT * FROM orders WHERE u_id = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, u_id);
		ResultSet resultSet = pStatement.executeQuery();
		
		while (resultSet.next()) {
			Order order = new Order();
			order.setO_id(resultSet.getInt("o_id"));
			order.setP_id(resultSet.getInt("p_id"));
			order.setU_id(resultSet.getInt("u_id"));
			order.setO_quantity(resultSet.getInt("o_quantity"));
			order.setDate(resultSet.getString("o_date"));
			
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.getSingleProduct(order.getP_id());
			order.setName(product.getName());
			order.setCategory(product.getCategory());
			order.setPrice(product.getPrice()*order.getO_quantity());
			orders.add(order);
		}
		
		JDBCUtil.closeConnection(connection);
		return orders;
	}
	
	public void deleteOrder(int o_id) throws SQLException {
		Connection connection = JDBCUtil.getConnection();
		String sql = "DELETE FROM orders WHERE o_id = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, o_id);
		pStatement.executeUpdate();
		JDBCUtil.closeConnection(connection);
	}
}
