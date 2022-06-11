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
			// To GET the customer and customer.toString().
			CustomerDetails cusDet = mySession.get(CustomerDetails.class, 1);
			System.out.println(cusDet);			
			System.out.println(cusDet.getCustomer());			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			/*	Para evitar el 'Error Leak', hay que trasladar el comando
			 * 	'mySession.close()' al bloque finally, antes de cerrar el objeto
			 * 	SessionFactory myFactory.	*/
			mySession.close();
			myFactory.close();			
		}
	}

}
