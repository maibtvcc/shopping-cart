package shopping_cart.model;

public class Odetail extends Product{
	private int o_id;
	private int quantity;
	private int p_id;
	
	public Odetail() {}
	public Odetail(int o_id, int quantity) {
		super();
		this.o_id = o_id;
		this.quantity = quantity;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal_price() {
	return getPrice()*getQuantity();
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
	
	
}
