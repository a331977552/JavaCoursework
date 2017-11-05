package uk.co.java.coursework.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Taxi;

public interface TaxiServiceInterface {
	public List<Taxi>  getAllTaxis();
	public Taxi  getTaxiById(Long id);
	public Taxi  getTaxiByRegistration(String registrationNumber);
	public List<Taxi>  getTaxiByNumberofSeats(Integer numberOfSeats);
	
	public Taxi addTaxi(Taxi taxi) throws ConstraintViolationException, ValidationException, Exception;
	public Taxi updateTaxi(Taxi taxi);
	
}
