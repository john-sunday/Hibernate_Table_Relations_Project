package es.pills.hibernateconnection;

import java.util.GregorianCalendar;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CascadingOrderInsertion {

	public static void main(String[] args) {
		SessionFactory myFactory = 
				new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerDetails.class)
				.addAnnotatedClass(CustomerOrder.class)
				.buildSessionFactory();
		Session mySession = myFactory.openSession();
		//Scanner sc = new Scanner(System.in);
		try {
			/*
			 * System.out.println("Enter the ID customer...."); String id = sc.nextLine();
			 */
			
			mySession.beginTransaction();			
			// We get the customer of the DB customer table.
			Customer customer = mySession.get(Customer.class, 5);
			// Create one or many CustomerOrder/s.			
			CustomerOrder customerOrder1 = new CustomerOrder(new GregorianCalendar(2020,9,3));
			CustomerOrder customerOrder2 = new CustomerOrder(new GregorianCalendar(2020,12,29));
			CustomerOrder customerOrder3 = new CustomerOrder(new GregorianCalendar(2020,1,1));
			// Agregate Order/s to Customer created.
			customer.addOrder(customerOrder1);
			customer.addOrder(customerOrder2);
			customer.addOrder(customerOrder3);
			
			mySession.save(customerOrder1);
			mySession.save(customerOrder2);
			mySession.save(customerOrder3);
						
			mySession.getTransaction().commit();
			System.out.println("Order/s Sucessfully INSERTED in DB");										
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			mySession.close();
			myFactory.close();
		}
	}
}
