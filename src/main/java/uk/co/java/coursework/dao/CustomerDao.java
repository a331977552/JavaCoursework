package uk.co.java.coursework.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.exception.UniqueEmailException;
import uk.co.java.coursework.validator.CommonValidator;

public class CustomerDao implements CustomerDaoInterface {

	@Inject
	private @Named("logger") Logger log;


	@Inject
	private EntityManager em;

	@Override
	public List<Customer> getAllCustomers() {

		TypedQuery<Customer> query = em.createNamedQuery(Customer.FIND_ALL, Customer.class);
		return query.getResultList();

	}

	@Override
	public Customer getCustomerById(Long id) {
		return em.find(Customer.class, id);
	}

	@Override
	public Customer createCustomer(Customer customer)
			throws ConstraintViolationException, ValidationException, UniqueEmailException, Exception {



		em.persist(customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer merge = em.merge(customer);

		return merge;
	}

	@Override
	public Customer getCustomerByEmail(String email) throws NoResultException {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root).where(builder.equal(root.get("email"), email));
		return em.createQuery(query).getSingleResult();
	}

}
