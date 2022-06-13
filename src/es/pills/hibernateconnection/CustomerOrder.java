package es.pills.hibernateconnection;

import java.sql.Date;

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
	private Date orderDate;
	@Column(name="payment_method")
	private String paymentMethod;
	@Column(name="customer_id")
	private int customerId;
	
	// Many por la clase donde estamos -> CustomerOrder.
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	// @JoinColumn hace referencia a la columna de la tabla de la clase donde nos encontramos -> CustomerOrder
	@JoinColumn(name="customer_id")
	private Customer customer;

	// Constructors.
	public CustomerOrder() {
		super();
	}
	public CustomerOrder(Date orderDate) {
		super();
		this.orderDate = orderDate;
	}
	public CustomerOrder(String paymentMethod, int customerId) {
		super();
		this.paymentMethod = paymentMethod;
		this.customerId = customerId;
	}
	// Getters and Setters.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", orderDate=" + orderDate + ", paymentMethod=" + paymentMethod
				+ ", customerId=" + customerId + "]";
	}
	
}
