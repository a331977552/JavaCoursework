package uk.co.java.coursework.dao;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Booking;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.entity.Taxi;

public interface BookingDaoInterface {
	public List<Booking> getAllBookings();

	public Booking getBookingById(Long id);

	public List<Booking> getBookingByCustomer(Customer customer);

	public void deleteBookingById(Long id);


	public Booking createBooking(Booking booking) throws ConstraintViolationException, ValidationException, Exception;

	public Booking updateBooking(Booking booking);

	public Booking getBookingByDateAndTaxiId(Date bookingDate, Long taxiId);

	void deleteBookingByCustomer(Long customerId);

}
