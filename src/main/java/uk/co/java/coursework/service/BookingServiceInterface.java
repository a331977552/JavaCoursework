package uk.co.java.coursework.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Booking;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.exception.UniqueDateTaxiIdException;

public interface BookingServiceInterface {

	public List<Booking> getAllBookings();

	public Booking getBookingById(Long id);

	public List<Booking> getBookingByCustomer(Customer customer);

	public void deleteBookingById(Long id);


	public Booking createBooking(Booking booking) throws ConstraintViolationException,UniqueDateTaxiIdException, ValidationException, Exception;

	public Booking updateBooking(Booking booking);

	void deleteBookingByCustomer(Long customerId) throws ValidationException;

}
