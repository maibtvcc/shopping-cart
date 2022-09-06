package test;

import java.util.ArrayList;
import java.util.List;

import shopping_cart.model.Product;

public class testArraylist {
	public static void main(String[] args) {
		ArrayList<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setId(1);
		products.add(product);
		System.out.println(products.size());
		products.remove(product);
		System.out.println(products.size());
	}
}
