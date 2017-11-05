package uk.co.java.coursework.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Customer;

public interface CustomerServiceInterface {

	
	public List<Customer>  getAllCustomers();
	public Customer  getCustomerById(Long id);
	public Customer createCustomer(Customer customer) throws ConstraintViolationException, ValidationException, Exception;
	public Customer updateCustomer(Customer customer);
	
	
	
	
}
