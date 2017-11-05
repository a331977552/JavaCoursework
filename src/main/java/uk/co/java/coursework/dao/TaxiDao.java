package uk.co.java.coursework.dao;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Taxi;

public class TaxiDao implements TaxiDaoInterface {

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
		return null;
	}

}
