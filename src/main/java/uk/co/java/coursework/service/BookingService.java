package uk.co.java.coursework.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.dao.BookingDaoInterface;

import uk.co.java.coursework.entity.Booking;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.exception.UniqueDateTaxiIdException;
import uk.co.java.coursework.validator.CommonValidator;

@Dependent
public class BookingService implements BookingServiceInterface {

	@Inject
	BookingDaoInterface dao;
	@Inject
	private CommonValidator validator;

	@Override
	public List<Booking> getAllBookings() {

		return dao.getAllBookings();
	}

	@Override
	public Booking getBookingById(Long id) {
		if (id == null || id < 0)
			return null;
		return dao.getBookingById(id);
	}

	@Override
	public List<Booking> getBookingByCustomer(Customer customer) {
		if (customer == null || customer.getId()==null )
			return null;
		
		
		return dao.getBookingByCustomer(customer);
	}

	@Override
	public void deleteBookingById(Long id) {
		dao.deleteBookingById(id);
	}

	@Override
	public void deleteBookingByCustomer(Long customerId)throws ValidationException {
		if (customerId == null || customerId<0 )
		{
			throw new ValidationException("customer's id cannnot be null or negative");
		
		}
	dao.deleteBookingByCustomer(customerId);
	}

	@Override
	public Booking createBooking(Booking booking) throws ConstraintViolationException, UniqueDateTaxiIdException,ValidationException, Exception {
		
		validator.validateBean(booking);
		
		try {
			Booking bookingGot=dao.getBookingByDateAndTaxiId(booking.getBookingdate(),booking.getTaxiId());
			if(bookingGot!=null) {
				throw new UniqueDateTaxiIdException("this taxi is in service at this date, please try another date or taxi");
			}
		}catch(NoResultException exception) {
			
		}	
		return 	dao.createBooking(booking);
	}

	@Override
	public Booking updateBooking(Booking booking) {
		return dao.updateBooking(booking);
	}

}
