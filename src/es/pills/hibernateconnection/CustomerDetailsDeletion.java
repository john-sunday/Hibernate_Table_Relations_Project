package es.pills.hibernateconnection;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomerDetailsDeletion {

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
			CustomerDetails cusDet = mySession.get(CustomerDetails.class, 4);
			//Customer c = mySession.get(Customer.class, 3);
			System.out.println(cusDet);			
			System.out.println(cusDet.getCustomer());
			System.out.println("\nDELETING ONLY Customer Details......\n");					
			
			/*
			 * mySession .createQuery("DELETE FROM CustomerDetails cd where cd.id=" +
			 * cusDet.getId()) .executeUpdate();
			 */	
			/**
			 * IMPORTANTE
			 * Ahora si he conseguido el objetivo, eliminar registro de la tabla 'customer_details'
			 * sin hacerlo de la tabla 'customer':
			 * 	-eliminando la foreign key 'constraint' de tabla customer
			 * 	-creando 'constraint' en tabla 'customer_details' con 'ON DELETE NO ACTION'
			 * 	-eliminando de la clase CustomerDetails la anotación cascade -->
			 *  --> '@OneToOne(cascade=CascadeType.ALL)'
			 * 	en el campo de clase de tipo 'Customer customer'
			 * 
			 * Con la pega de que ya no podemos borrar registro de tabla 'customer' en cascada.
			 * Pero tiene más sentido poder eliminar sólo los customer details, que solo los customer.
			 * Y con el ENORME PROBLEMA de que no podemos realizar nuevas inserciones.
			 * 
			 * Aunque para una app, lo más 'normal' y lógico sería como estaba antes:
			 *	-foreign key en tabla customer con ON DELETE CASCADE ON UPDATE NO ACTION
			 * Para que no se puedan eliminar registros sólo de la tabla 'customer_details', 
			 * y para que si se quiere eliminar algún registro, tenga que ser un registro en la 
			 * tabla customer, y en cascada en la tabla hija, 'customer_details', el/los registros. 
			 * 
			 * FINÁLMENTE:
			 * Vuelvemos a modificar la estructura de la base de datos para dejarla como estaba:
			 * 	-volvemos a añadir la cascada en la anotación de la clase 'CustomerDetails' @OneToOne(cascade=CascadeType.ALL)
			 * 	-eliminamos las tres tablas(customer,customer_details y customer_order)
			 * 	-añadimos las tres tablas, pero con la FOREIGN KEY en customer con ON DELETE CASCADE.
			 */
			cusDet.getCustomer().setCustomerDetails(null); 
			mySession.delete(cusDet);
			 
			mySession.getTransaction().commit();
			System.out.println("Customer Details DELETED");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			mySession.close();
			myFactory.close();
		}
	}
}
