package shopping_cart_ver3.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "oDetails")
public class Odetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "o_id")
	private int o_id;
	private int quantity;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
	@JoinColumn(name = "p_id")
	private int p_id;
	
	public Odetail() {}
	public Odetail(int o_id, int quantity,int p_id) {
		super();
		this.o_id = o_id;
		this.quantity = quantity;
		this.p_id = p_id;
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
	
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
		
}
