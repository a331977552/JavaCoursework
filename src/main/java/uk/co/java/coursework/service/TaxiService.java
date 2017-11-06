package uk.co.java.coursework.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.dao.TaxiDaoInterface;
import uk.co.java.coursework.entity.Taxi;
import uk.co.java.coursework.exception.UniqueRegistrationNumberException;
import uk.co.java.coursework.validator.CommonValidator;

@Dependent
public class TaxiService implements TaxiServiceInterface {
	@Inject
	TaxiDaoInterface dao;
	@Inject
	private CommonValidator validator;

	@Override
	public List<Taxi> getAllTaxis() {

		return dao.getAllTaxis();
	}

	@Override
	public Taxi getTaxiById(Long id) {
		if (id == null || id < 0)
			return null;
		return dao.getTaxiById(id);
	}

	@Override
	public Taxi getTaxiByRegistration(String registrationNumber) {
		return dao.getTaxiByRegistration(registrationNumber);
	}

	@Override
	public List<Taxi> getTaxiByNumberofSeats(Integer numberOfSeats) {
		return dao.getTaxiByNumberofSeats(numberOfSeats);
	}

	@Override
	public Taxi addTaxi(Taxi taxi) throws ConstraintViolationException, ValidationException, Exception {

		validator.validateBean(taxi);
		try {
			Taxi taxiByRegistration = dao.getTaxiByRegistration(taxi.getRegistrationNumber());
			if (taxiByRegistration != null) {
				throw new UniqueRegistrationNumberException("taxi's registration number has  already been registered");
			}
			
		}catch(NoResultException e) {
			
		}

		return dao.addTaxi(taxi);
	}

	@Override
	public Taxi updateTaxi(Taxi taxi) {
		if (taxi.getId() == null || taxi.getId() < 0)
			return null;
		return dao.updateTaxi(taxi);
	}

}
