package shopping_cart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shopping_cart.connection.JDBCUtil;
import shopping_cart.model.Odetail;
import shopping_cart.model.Order2;

public class Order2DAO {
	
	public Order2DAO() {	}
	
	public boolean insertOrder(Order2 order) {
		try {
			Connection connection = JDBCUtil.getConnection();
			String sql = "INSERT INTO orders2 (u_id,date) VALUES (?,?)";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, order.getU_id());
			pStatement.setString(2, order.getDate());
			pStatement.executeUpdate();
			
			sql = "SELECT o_id FROM orders2 where u_id=? order by o_id desc limit 1";
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, order.getU_id());
			ResultSet rs= pStatement.executeQuery();
			rs.next();
			int o_id = rs.getInt("o_id");
			System.out.println(o_id);
			
			ArrayList<Odetail> odetails = order.getOdetails();
			for (Odetail odetail : odetails) {
				sql = "INSERT INTO oDetails (o_id,p_id,quantity) VALUES (?,?,?)";
				pStatement = connection.prepareStatement(sql);
				pStatement.setInt(1, o_id);
				pStatement.setInt(2, odetail.getP_id());
				pStatement.setInt(3, odetail.getQuantity());
				pStatement.executeUpdate();
			}
			
			
			JDBCUtil.closeConnection(connection);
			return true;
		} catch (SQLException e) {
			return false;
		}	
	}
	
	public ArrayList<Odetail> odetails(int o_id) throws SQLException {
		ArrayList<Odetail> odetails = new ArrayList<Odetail>();
		Connection connection = JDBCUtil.getConnection();
		String sql = "SELECT * FROM (SELECT * FROM oDetails WHERE o_id = ?) as t1 JOIN products ON products.id=t1.p_id ";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, o_id);
		ResultSet resultSet = pStatement.executeQuery();
		
		while (resultSet.next()) {
			Odetail odetail = new Odetail();
			odetail.setO_id(resultSet.getInt("o_id"));
			odetail.setName(resultSet.getString("name"));
			odetail.setCategory(resultSet.getString("Category"));
			odetail.setQuantity(resultSet.getInt("quantity"));
			odetail.setPrice(resultSet.getDouble("price"));			
			odetails.add(odetail);
		}
		
		JDBCUtil.closeConnection(connection);
		return odetails;
	}
	
	
	public void deleteOrder(int o_id,int u_id) throws SQLException {
		Connection connection = JDBCUtil.getConnection();
		String sql = "UPDATE orders2 SET status = 0 	WHERE o_id = ? AND u_id=?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, o_id);
		pStatement.setInt(2, u_id);
		pStatement.executeUpdate();
		JDBCUtil.closeConnection(connection);
	}
	
	public ArrayList<Order2> userOrders(int u_id) throws SQLException {
		ArrayList<Order2> orders = new ArrayList<Order2>();
		Connection connection = JDBCUtil.getConnection();
		String sql = "SELECT * FROM \r\n"
				+ "(SELECT orders2.o_id, u_id,date,status,p_id,quantity FROM orders2 JOIN oDetails ON orders2.o_id=oDetails.o_id WHERE u_id=?)\r\n"
				+ "as t1 JOIN products ON products.id=t1.p_id ";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, u_id);
		ResultSet resultSet = pStatement.executeQuery();
		int o_id = 0;
		ArrayList<Odetail> odetails = new ArrayList<Odetail>();
		Order2 order = null;

		while (resultSet.next()) {
			Odetail odetail = new Odetail();
			odetail.setP_id(resultSet.getInt("p_id"));
			odetail.setName(resultSet.getString("name"));
			odetail.setCategory(resultSet.getString("Category"));
			odetail.setQuantity(resultSet.getInt("quantity"));
			odetail.setPrice(resultSet.getDouble("price"));
			
			if (o_id != resultSet.getInt("o_id")) {
				if (o_id!=0) {
					order.setOdetails(odetails);
					orders.add(order);
					}
				order = new Order2();
				o_id = resultSet.getInt("o_id");
				order.setO_id(o_id);
				order.setDate(resultSet.getString("date"));
				odetails = new ArrayList<Odetail>();
			}
			
			odetails.add(odetail);			
		}
		order.setOdetails(odetails);
		orders.add(order);
		
		JDBCUtil.closeConnection(connection);
		return orders;
	}
	
}
