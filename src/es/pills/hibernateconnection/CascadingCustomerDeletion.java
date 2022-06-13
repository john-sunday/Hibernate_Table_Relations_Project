package es.pills.hibernateconnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CascadingCustomerDeletion {

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
			CustomerDetails cusDet = mySession.get(CustomerDetails.class, 3);
			//Customer c = mySession.get(Customer.class, 3);
			System.out.println(cusDet);			
			System.out.println(cusDet.getCustomer());
			System.out.println("DELETING CASCADE Customer Details first and Customer second......");			
			/**
			 * Importante:
			 * Lo he hecho igual que Juan, y no puedo eliminar con Session.delete(),
			 * necesito usar el método Session.createQuery(Delete.....).executeUpdate();
			 * 
			 * ------------- Más Importante ----------
			 * Si establecemos a null la propiedad customerDetails del customer si podemos eliminar
			 * el registro de la tabla 'customer_details'.
			 * Aclaración --> ahora la tabla padre es la antigua tabla hija(customer_details), y la
			 * tabla hija es la anterior tabla padre(customer). La foreign key está eliminada de la
			 * la tabla customer, y se ha añadido a customer_details, para eliminar registros de 
			 * customer_details, sin eliminar el registro de customer(vídeo 59):
			 * 	-eliminando el foreign key de la tabla customer
			 * 	-creando un foreign key en la tabla customer_details con 'DELETE NO ACTION'
			 */
			cusDet.getCustomer().setCustomerDetails(null);
			mySession.delete(cusDet);			
			
			/*
			 * mySession .createQuery("DELETE FROM Customer c where c.id=" + cusDet.getId())
			 * .executeUpdate();
			 */
			 
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
