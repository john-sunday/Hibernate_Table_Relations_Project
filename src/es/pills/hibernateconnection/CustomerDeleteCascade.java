package es.pills.hibernateconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomerDeleteCascade {

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
			//Customer customer = new Customer("Alejandra","Matamoros","Av Albufera");
			//Customer customer = new Customer("Maria","de la O","Calle Pasodoble");
			
			//customer.setCustomerDetails(new CustomerDetails("www.jose.r3p","987654321","Great Heart, Gold Heart"));
						
			mySession.beginTransaction();
			// To GET the customer target to DELETE.
			Customer c = mySession.get(Customer.class, 4);
			if (c != null) {
				mySession.delete(c);
				mySession.getTransaction().commit();
				System.out.println("Record Sucessfully DELETED in DB");
			}else {
				System.out.println(" WARNING --> Customer doesn't exists in data base");
			}
			
			mySession.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {			
			myFactory.close();
		}
	}
}
