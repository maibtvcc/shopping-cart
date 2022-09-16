package shopping_cart.model;

public class Order extends Product{
	private int o_id;
	private int p_id;
	private int u_id;
	private int o_quantity;
	private String date;
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getO_quantity() {
		return o_quantity;
	}
	public void setO_quantity(int o_quantity) {
		this.o_quantity = o_quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Order() {	}
	public Order(int o_id, int p_id, int u_id, int o_quantity, String date) {
		super();
		this.o_id = o_id;
		this.p_id = p_id;
		this.u_id = u_id;
		this.o_quantity = o_quantity;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Order [o_id=" + o_id + ", p_id=" + p_id + ", u_id=" + u_id + ", o_quantity=" + o_quantity + ", date="
				+ date + "]";
	}
	
	
}
