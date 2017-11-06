package uk.co.java.coursework.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.entity.Taxi;

public class TaxiDao implements TaxiDaoInterface {

	
	@Inject
	EntityManager manager;
	
	
	@Override
	public List<Taxi> getAllTaxis() {
		
		Query createNativeQuery = manager.createNativeQuery("select * from taxi", Taxi.class);
		List<Taxi> resultList = createNativeQuery.getResultList();
		return resultList;
	}

	@Override
	public Taxi getTaxiById(Long id) {
		
		return manager.find(Taxi.class, id);
	}

	@Override
	public Taxi getTaxiByRegistration(String registrationNumber)throws NoResultException {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Taxi> query = builder.createQuery(Taxi.class);
		Root<Taxi> root = query.from(Taxi.class);
		query.select(root).where(builder.equal(root.get("registrationNumber"), registrationNumber));
		return manager.createQuery(query).getSingleResult();
		
	}

	@Override
	public List<Taxi> getTaxiByNumberofSeats(Integer numberOfSeats) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Taxi> query = builder.createQuery(Taxi.class);
		Root<Taxi> root = query.from(Taxi.class);
		query.select(root).where(builder.equal(root.get("numberOfSeats"), numberOfSeats));
		return manager.createQuery(query).getResultList();
	}

	@Override
	public Taxi addTaxi(Taxi taxi) throws ConstraintViolationException, ValidationException, Exception {
		
		manager.persist(taxi);
		return taxi;
	}

	@Override
	public Taxi updateTaxi(Taxi taxi) {
		
		return manager.merge(taxi);
	}

}
