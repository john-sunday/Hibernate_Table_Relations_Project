package es.pills.hibernateconnection;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCustomerOrder {

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
			Customer customer = mySession.get(Customer.class, 1);
			System.out.println(customer.toString());
			System.out.println(customer.getCustomerOrders());
			
			/*
			 * List<CustomerOrder> orders = customer.getCustomerOrders(); for (CustomerOrder
			 * cOrder:orders) { System.out.println(cOrder); }
			 */
						
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
