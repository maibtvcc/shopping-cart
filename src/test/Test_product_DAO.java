package test;

import java.sql.SQLException;
import java.util.List;


import shopping_cart.DAO.ProductDAO;
import shopping_cart.model.Product;

public class Test_product_DAO {
	public static void main(String[] args) throws SQLException {
		ProductDAO productDAO=new ProductDAO();
		List<Product> products = productDAO.getAllProduct();
		for (Product product : products) {System.out.println(product.toString());}
	}
}
