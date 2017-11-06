package uk.co.java.coursework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

@Entity
@XmlRootElement
@Table(name="taxi",uniqueConstraints= {@UniqueConstraint(columnNames="registrationNumber")})
public class Taxi {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Length(min=7,max=7,message="registration Number's size has to be 7")
	@Pattern(regexp="^[0-9a-zA-Z]{7}$",message="registeratrionNumber has to be in pattern of ^[0-9a-zA-Z]{7}$")
	@Column(name="registrationNumber",nullable=false,unique=true)
	private String registrationNumber;
				   
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getRegistrationNumber() {
		return registrationNumber;
	}



	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}



	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}



	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	
	@Column(name="numberOfSeats")
	private Integer numberOfSeats;
	

}
