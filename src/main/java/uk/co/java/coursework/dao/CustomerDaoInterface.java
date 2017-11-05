package uk.co.java.coursework.dao;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.exception.UniqueEmailException;

public interface CustomerDaoInterface {

	public List<Customer>  getAllCustomers();
	public Customer  getCustomerById(Long id);
	public Customer createCustomer(Customer customer) throws ConstraintViolationException, ValidationException,UniqueEmailException, Exception;
	public Customer updateCustomer(Customer customer);
	public Customer getCustomerByEmail(String email);
	
	
	
}
