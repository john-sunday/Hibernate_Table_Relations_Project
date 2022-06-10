package es.pills.hibernateconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCustomer {

	public static void main(String[] args) {
		SessionFactory myFactory = 
				new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerDetails.class)//--> Faltaba añadir la otra clase anotada.
				.buildSessionFactory();
		Session mySession = myFactory.openSession();
		
		try {				
			mySession.beginTransaction();
			// To GET the customer_details target to DELETE.
			CustomerDetails cusDet = mySession.get(CustomerDetails.class, 3);
			System.out.println(cusDet);			
			System.out.println(cusDet.getCustomer());
			
			/*
			 * if (c != null) { mySession.delete(c); mySession.getTransaction().commit();
			 * System.out.println("Record Sucessfully DELETED in DB"); }else {
			 * System.out.println(" WARNING --> Customer doesn't exists in data base"); }
			 */
			
			mySession.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {			
			myFactory.close();
		}
	}

}
