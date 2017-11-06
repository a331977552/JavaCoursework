package uk.co.java.coursework.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

@Entity
@NamedQueries({
				@NamedQuery(name=Customer.FIND_ALL,query="SELECT c FROM Customer c")
				,@NamedQuery(name=Customer.FIND_BY_EMAIL,query="SELECT c FROM Customer c where c.email= :email")
				,@NamedQuery(name=Customer.FIND_BY_PHONENUMBER,query="SELECT c FROM Customer c where c.phoneNumber= :phoneNumber")
				})

@XmlRootElement
@Table(name="customer",uniqueConstraints= {@UniqueConstraint(columnNames="email")})
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4120642958455387878L;
	public static final String FIND_BY_EMAIL = "find_by_email";
	public static final String FIND_ALL = "find_all";
	public static final String FIND_BY_PHONENUMBER = "find_by_phoneNumber";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
 	@NotNull(message="eamil cannot be null")
  	@Column(name="email",unique=true)
 	
 	@Email(message = "The email address must be in the format of name@domain.com")
	private String email;
 	
 	@Pattern(regexp = "^(0[0-9]{10})$",message="phone Number have to start with 0 and follow by 10 digital numbers")

 	@Column(name="phoneNumber",nullable=false) 	
 	private String phoneNumber;
	
 	@NotNull
  	@Size(min=1,max=49,message="name's size must be between 1 and 49")
 	@Column(name="name",nullable=false)
 	private String name;
 	
 	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
