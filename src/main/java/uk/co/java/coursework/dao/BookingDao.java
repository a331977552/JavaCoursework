package uk.co.java.coursework.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import uk.co.java.coursework.entity.Booking;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.entity.Taxi;

public class BookingDao implements BookingDaoInterface {

	@Inject
	EntityManager manager;
	
	@Override
	public List<Booking> getAllBookings() {
		Query createNativeQuery = manager.createNativeQuery("select * from booking",Booking.class);
		List<Booking> resultList = createNativeQuery.getResultList();
		return resultList;
	}

	@Override
	public Booking getBookingById(Long id) {
		
		return manager.find(Booking.class, id);
	}

	@Override
	public List<Booking> getBookingByCustomer(Customer customer) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Booking> query = builder.createQuery(Booking.class);
		Root<Booking> root = query.from(Booking.class);
		query.select(root).where(builder.equal(root.get("customerid"),customer.getId()));
		return manager.createQuery(query).getResultList();
		
	}

	@Override
	public void deleteBookingById(Long id) {
		manager.remove(id);
		
	}

	@Override
	public void deleteBookingByCustomer(Long customerId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaDelete<Booking> query = builder.createCriteriaDelete(Booking.class);
		
		Root<Booking> root = query.from(Booking.class);
		query.where(builder.equal(root.get("customerId"),customerId));
		manager.createQuery(query).executeUpdate();
	}

	@Override
	public Booking createBooking(Booking booking) throws ConstraintViolationException, ValidationException, Exception {
		manager.persist(booking);
		return booking;
	}

	@Override
	public Booking updateBooking(Booking booking) {
		
		return manager.merge(booking);
	}

	@Override
	public Booking getBookingByDateAndTaxiId(Date bookingDate, Long taxiId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Booking> query = builder.createQuery(Booking.class);
		Root<Booking> root = query.from(Booking.class);
		
		
		
		query.select(root).where(builder.and(builder.equal(root.get("bookingdate"),bookingDate),builder.equal(root.get("taxiId"),taxiId)));
		return manager.createQuery(query).getSingleResult();
	}

}
