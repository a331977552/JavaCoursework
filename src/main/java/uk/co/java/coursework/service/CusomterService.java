package uk.co.java.coursework.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.dao.CustomerDao;
import uk.co.java.coursework.dao.CustomerDaoInterface;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.exception.UniqueEmailException;
import uk.co.java.coursework.utils.CustomerValidator;

@Dependent
public class CusomterService implements CustomerServiceInterface{

	
	@Inject
	CustomerDaoInterface dao;
	@Inject
	private CustomerValidator validator;
	@Override
	public List<Customer> getAllCustomers() {
		
		return dao.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(Long id) {
		if(id==null|| id<0)
			return null;
		return dao.getCustomerById(id);
	}

	@Override
	public Customer createCustomer(Customer customer) throws ConstraintViolationException, ValidationException, Exception {
				
		try {
			Customer customerByEmail = dao.getCustomerByEmail(customer.getEmail());
			if (customerByEmail != null)
				throw new UniqueEmailException("this email has been used by another user,please change the email");
		} catch (NoResultException e) {
			//ignore,this means there is not user who already used this email address	
		}
		validator.validateContact(customer);
		return dao.createCustomer(customer);
	
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		
		return dao.updateCustomer(customer);
		
	}

	
	
}
