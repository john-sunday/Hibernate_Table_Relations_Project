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
			mySession.beginTransaction();
			// To GET the customer to DELETE.
			CustomerDetails cusDet = mySession.get(CustomerDetails.class, 7);
			//Customer c = mySession.get(Customer.class, 3);
			System.out.println(cusDet);			
			System.out.println(cusDet.getCustomer());
			System.out.println("DELETING CASCADE Customer Details first and Customer second......");			
			/**
			 * Importante:
			 * Lo he hecho igual que Juan, y no puedo eliminar con Session.delete(),
			 * necesito usar el método Session.createQuery(Delete.....).executeUpdate();
			 */
			//mySession.delete(cusDet);			
			mySession
			.createQuery("DELETE FROM CustomerDetails cd where cd.id=7")
			.executeUpdate();
			mySession.getTransaction().commit();
			System.out.println("Customer and Details DELETED");
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
