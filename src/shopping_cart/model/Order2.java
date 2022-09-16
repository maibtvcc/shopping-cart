package shopping_cart.model;

import java.util.ArrayList;
import java.util.List;

public class Order2 {
	private int o_id;
	private int u_id;
	private String date;
	private ArrayList<Odetail> odetails;
	
	public Order2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order2(int o_id, int u_id, String date, ArrayList<Odetail> odetails) {		this.o_id = o_id;
		this.u_id = u_id;
		this.date = date;
		this.odetails = odetails;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Odetail> getOdetails() {
		return odetails;
	}

	public void setOdetails(ArrayList<Odetail> odetails) {
		this.odetails = odetails;
	}
	
	
}
