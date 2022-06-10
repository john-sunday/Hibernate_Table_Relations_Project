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
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="surname")
	private String surname;
	@Column(name="address")
	private String address;
	
	// Table Relation One to One.
	// ORM Mapping.
	// Relation Type(Cascade Type Operation):
	@OneToOne(cascade=CascadeType.ALL)
	// Field Relation:
	@JoinColumn(name="id")
	private CustomerDetails customerDetails;
	
	// Constructor default.
	public Customer() {
		super();
	}
	// Constructor parameters.
	// It isn't add the primary key, because is auto-numeric.
	public Customer(String name, String surname, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
	}
	// Getters and Setters.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + "]";
	}
	
	
}
