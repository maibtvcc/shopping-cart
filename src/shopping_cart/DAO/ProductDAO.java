package shopping_cart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import shopping_cart.connection.JDBCUtil;
import shopping_cart.model.Cart;
import shopping_cart.model.Product;

public class ProductDAO {

	public ProductDAO() {
	}
		
	public List<Product> getAllProduct() throws SQLException {
		List<Product> products= new ArrayList();
		Connection connection = JDBCUtil.getConnection();
		String sql = "Select * from products";
		Statement st = connection.createStatement();
		ResultSet resultSet=st.executeQuery(sql);
		while (resultSet.next()) {
			Product product = new Product();
			product.setId(resultSet.getInt("id"));
			product.setName(resultSet.getString("name"));
			product.setCategory(resultSet.getString("category"));
			product.setPrice(resultSet.getDouble("price"));
			product.setImage(resultSet.getString("image"));
			products.add(product);
		}
		JDBCUtil.closeConnection(connection);
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		
		List<Cart> products = new ArrayList<Cart>();
		try {
			Connection connection = JDBCUtil.getConnection();
			for (Cart cart : cartList) {
				String sql = "Select * from products where id = ?";
				PreparedStatement st = connection.prepareStatement(sql);
				st.setInt(1, cart.getId());
				ResultSet resultSet=st.executeQuery();
				while (resultSet.next()) {
					Cart product = new Cart();
					product.setId(resultSet.getInt("id"));
					product.setName(resultSet.getString("name"));
					product.setCategory(resultSet.getString("category"));
					product.setPrice(resultSet.getDouble("price")*cart.getQuantity());
					product.setQuantity(cart.getQuantity());
					products.add(product);
				}
			}
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	public Product getSingleProduct(int id) throws SQLException {
		Connection connection = JDBCUtil.getConnection();
		String sql = "Select * from products where id = ?";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet resultSet=st.executeQuery();
		Product product = new Product();
		while (resultSet.next()) {
			product.setId(resultSet.getInt("id"));
			product.setName(resultSet.getString("name"));
			product.setCategory(resultSet.getString("category"));
			product.setPrice(resultSet.getDouble("price"));
		}
		JDBCUtil.closeConnection(connection);
		return product;
	}

}
