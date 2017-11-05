package uk.co.java.coursework.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.dao.CustomerDaoInterface;
import uk.co.java.coursework.dao.TaxiDaoInterface;
import uk.co.java.coursework.entity.Taxi;
import uk.co.java.coursework.utils.CustomerValidator;

@Dependent
public class TaxiService implements TaxiServiceInterface {
	@Inject
	TaxiDaoInterface dao;
	@Inject
	private TaxiValidator validator;
	@Override
	public List<Taxi> getAllTaxis() {
		
		return null;
	}

	@Override
	public Taxi getTaxiById(Long id) {
		return null;
	}

	@Override
	public Taxi getTaxiByRegistration(String registrationNumber) {
		return null;
	}

	@Override
	public List<Taxi> getTaxiByNumberofSeats(Integer numberOfSeats) {
		return null;
	}

	@Override
	public Taxi addTaxi(Taxi taxi) throws ConstraintViolationException, ValidationException, Exception {
		return null;
	}

	@Override
	public Taxi updateTaxi(Taxi taxi) {
		// TODO Auto-generated method stub
		return null;
	}

}
