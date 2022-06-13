package es.pills.hibernateconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
			//Customer customer = mySession.get(Customer.class, 1);
			Query<Customer>query = mySession.createQuery("SELECT C FROM Customer C JOIN FETCH C.customerOrders WHERE C.id=:customerId",Customer.class);
			query.setParameter("customerId", 1);			
			Customer customer = query.getSingleResult();
			
			System.out.println(customer.toString());
			//System.out.println(customer.getCustomerOrders());
									
			mySession.getTransaction().commit();
			// This works with 'FetchType.EAGER':
			mySession.close();
			System.out.println(customer.getCustomerOrders());
			
			System.out.println("Order/s Sucessfully SHOWED from DB");										
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			//mySession.close();
			myFactory.close();
		}
	}
}
