package uk.co.java.coursework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.cache.Cache;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.co.java.coursework.entity.Booking;
import uk.co.java.coursework.entity.Common;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.exception.UniqueEmailException;
import uk.co.java.coursework.exception.UniqueDateTaxiIdException;
import uk.co.java.coursework.service.BookingServiceInterface;
import uk.co.java.coursework.service.CustomerServiceInterface;
import uk.co.java.coursework.utils.RestServiceException;

@Path("/booking")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/booking", description = "Operations about booking")
@Stateless
public class BookingController {
	@Inject
	BookingServiceInterface service;

	@GET
	@Cache
	@ApiOperation(value = "Fetch all Bookings", notes = "return a JSON array of Booking entities, if any")
	@Path("/getAllBookings")
	public Response getAllBookings() {
		List<Booking> all = service.getAllBookings();
		Common<List<Booking>> common=new Common<>("success",all,1);
		return Response.ok(common).build();

	}

	@GET
	@ApiOperation(value = "Fetch a certain Booking by id", notes = "return a JSON object of Booking entity, if exists")
	@Path("/getBookingById/{id:[0-9]+}")
	public Response getBookingById(@PathParam("id") Long id) {
		Booking booking = service.getBookingById(id);
		Common<Booking> common=new Common<>("success",booking,1);
		
		return Response.ok(common).build();

	}

	@POST
	@ApiOperation(value = "Create Booking by adding whole variables except id", notes = "return a JSON object of Booking entity, if succeed")
	@Path("/createBooking")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Booking created successfully."),
			@ApiResponse(code = 400, message = "Invalid Booking supplied in request body"),
			@ApiResponse(code = 200, message = "successfully add a Booking"),
			@ApiResponse(code = 409, message = "Booking supplied in request body conflicts with an existing Booking"),
			@ApiResponse(code = 500, message = "An unexpected error occurred whilst processing the request") })

	public Response createBooking(
			@ApiParam(value = "JSON representation of Booking object to be added to the database", required = true) Booking booking) {
		if (booking == null) {
			throw new RestServiceException("Bad Request", Response.Status.BAD_REQUEST);
		}

		try {
			Booking addedBooking = service.createBooking(booking);
			Common<Booking> common=new Common<>("success",addedBooking,1);
			return Response.ok(common).build();
		} catch (ConstraintViolationException e) {
			Map<String, String> responseObj = new HashMap<>();

			for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
				
				responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			throw new RestServiceException("Bad Request", responseObj, Response.Status.BAD_REQUEST, e);

		} catch (ValidationException e) {
			e.printStackTrace();
			throw new RestServiceException(e);
		}catch(UniqueDateTaxiIdException e) {
			String message = e.getMessage();
			Common<Booking> common=new Common<>("failure: "+message,null,0);
			return Response.ok(common).build();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new RestServiceException(e);
		}
		
		
	}
	@DELETE
	@ApiOperation(value = "delete Booking by customer", notes = "return result")
	@Path("/deleteBooking")
	public Response deleteBooking(
			@ApiParam(value = "JSON representation of Customer object to be used to"
					+ " delete certain booking record based on the customer's id", required = true) 
			Customer customer) {
		Common<Customer> common=new Common<>();
		if(customer==null || customer.getId()==null)
		{
			common.setStatusCode(0);
			common.setBean(customer);
			common.setMsg("failure : the customer's id cannot be null");
		}else {
			service.deleteBookingByCustomer(customer.getId());
			common.setMsg("successfully deleted certain booking records");
			common.setStatusCode(1);
		}
		
		return Response.ok(common).build();
		
	}
}
