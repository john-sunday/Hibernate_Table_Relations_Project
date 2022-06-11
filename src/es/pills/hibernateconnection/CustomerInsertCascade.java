package es.pills.hibernateconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomerInsertCascade {

	public static void main(String[] args) {
			
			SessionFactory myFactory = 
					new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Customer.class)
					.addAnnotatedClass(CustomerDetails.class)//--> Faltaba añadir la otra clase anotada.
					.buildSessionFactory();
			Session mySession = myFactory.openSession();
			
			try {
				//Customer customer = new Customer("Pepe","Ortin","Av.Republica");
				//Customer customer = new Customer("Concha","Revo","Calle Vallecas");
				//Customer customer = new Customer("Johnny","Good","Calle Florencia");
				Customer customer = new Customer("Alejandra","Matamoros","Av Albufera");
				//Customer customer = new Customer("Maria","de la O","Calle Pasodoble");
				//customer.setCustomerDetails(new CustomerDetails("www.jose.r3p","987654321","Great Heart, Gold Heart"));
				//customer.setCustomerDetails(new CustomerDetails("www.mary.r3p","123456789","She is our most valorated customer"));
				customer.setCustomerDetails(new CustomerDetails("www.alex.r3p","111111111","Simply Alex"));
				
				mySession.beginTransaction();
				mySession.save(customer);			
				mySession.getTransaction().commit();
				System.out.println("Record Sucessfully INSERT in DB");
				
				/*
				 * mySession.beginTransaction(); mySession.save(customer2);
				 * mySession.getTransaction().commit();
				 * System.out.println("Record Sucessfully INSERT in DB");
				 */
				
				mySession.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally {			
				myFactory.close();
			}
	}
}
