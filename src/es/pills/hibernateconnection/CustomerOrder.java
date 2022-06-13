package es.pills.hibernateconnection;


import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_order")
public class CustomerOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="order_date")
	private GregorianCalendar orderDate;
	@Column(name="payment_method")
	private String paymentMethod;
	
	/* No se puede repetir la columna de la tabla mapeada
	 * "customer_id"
	 * De hecho, Juan no crea el campo de clase 'customerId'.
	 * @Column(name="customer_id") 
	 * private int customerId;
	 */
	
	// Many por la clase donde estamos -> CustomerOrder.
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	// @JoinColumn hace referencia a la columna de la tabla de la clase donde nos encontramos -> CustomerOrder
	@JoinColumn(name="customer_id")
	private Customer customer;

	// Constructors.
	public CustomerOrder() {
		super();
	}
	public CustomerOrder(java.util.GregorianCalendar date) {
		super();
		this.orderDate = date;
	}
	public CustomerOrder(String paymentMethod, int id) {
		super();
		this.paymentMethod = paymentMethod;
		this.id = id;
	}
	// Getters and Setters.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public GregorianCalendar getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(GregorianCalendar orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Customer Order \n"
				+ "[id=" + id + "\n"
				+ "order date=" + orderDate + "\n" 
				+ "payment method=" + paymentMethod +"]" + "\n";
	}
	
}
