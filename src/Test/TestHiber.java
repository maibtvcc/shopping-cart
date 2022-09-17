package Test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import shopping_cart_ver3.connection.HibernateUtils;
import shopping_cart_ver3.model.Odetail;

public class TestHiber {
	public static void main(String[] args) {
		Session session = HibernateUtils.getFactory().openSession();
		Query<Odetail>  query = session.createQuery("FROM oDetails");
		List<Odetail> odetails = query.list();
		odetails.forEach(o-> System.out.println(o.getQuantity()));
		session.close();
	}
}
