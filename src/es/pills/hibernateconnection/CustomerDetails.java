package es.pills.hibernateconnection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***
 * IMPORTANTE:
 * Importamos del paquete 'javax.persistence', no de 'org.hibernate.annotations',
 * como podría pensarse.
 * Como a mi no me aparece, lo he tenido que descargar:
 * http://www.java2s.com/Code/Jar/j/Downloadjavaxpersistence200jar.htm
 * 
 */


// Anotaciones necesarias para se haga mapeo.
@Entity
@Table(name="customer_details")
public class CustomerDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="web")
	private String web;
	@Column(name="phone")
	private String phone;
	@Column(name="comments")
	private String comments;
	
	// One to One:
	// Field name created in Customer class:
	@OneToOne(mappedBy="customerDetails")
	// Field Relation:
	@JoinColumn(name="id")
	private Customer customer;
	
	// Constructor default.
	public CustomerDetails() {
		super();
	}
	// Constructor parameters.
	// Primary key is isn't added, because is auto-numeric.
	public CustomerDetails(String web, String phone, String comments) {
		super();
		this.web = web;
		this.phone = phone;
		this.comments = comments;
	}
	// Getters and Setters.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "CustomerDetails [id=" + id + ", web=" + web + ", phone=" + phone + ", comments=" + comments
				+ ", customer=" + customer + "]";
	}	
}
