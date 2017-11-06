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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.cache.Cache;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.co.java.coursework.entity.Common;
import uk.co.java.coursework.entity.Customer;
import uk.co.java.coursework.entity.Taxi;
import uk.co.java.coursework.exception.UniqueEmailException;
import uk.co.java.coursework.exception.UniqueRegistrationNumberException;
import uk.co.java.coursework.service.CustomerServiceInterface;
import uk.co.java.coursework.service.TaxiServiceInterface;
import uk.co.java.coursework.utils.RestServiceException;

@Path("/taxi")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/taxi", description = "Operations about taxi")
@Stateless
public class TaxiController {

	@Inject
	TaxiServiceInterface service;

	@GET
	@Cache
	@ApiOperation(value = "Fetch all Taxis", notes = "return a JSON array of taxis entities, if any")
	@Path("/getAllTaxis")
	public Response getAllCustomers() {
		List<Taxi> all = service.getAllTaxis();
		Common<List<Taxi>> common=new Common<>("success",all,1);
		return Response.ok(common).build();

	}

	@GET
	@ApiOperation(value = "Fetch a certain Taxi by id", notes = "return a JSON object of Taxi entity, if exists")
	@Path("/getTaxiById/{id:[0-9]+}")
	public Response getAllCustomerById(@PathParam("id") Long id) {
		Taxi taxi = service.getTaxiById(id);
		Common<Taxi> common=new Common<>("success",taxi,1);
		
		return Response.ok(common).build();

	}

	@POST
	@ApiOperation(value = "add a Taxi by adding whole variables except id", notes = "return a JSON object of Taxi entity, if succeed")
	@Path("/addTaxi")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Taxi added successfully."),
			@ApiResponse(code = 400, message = "Invalid Taxi supplied in request body"),
			@ApiResponse(code = 200, message = "successfully add a Taxi"),
			@ApiResponse(code = 409, message = "Taxi supplied in request body conflicts with an existing Taxi"),
			@ApiResponse(code = 500, message = "An unexpected error occurred whilst processing the request") })

	public Response addTaxi(
			@ApiParam(value = "JSON representation of Taxi object to be added to the database", required = true) Taxi taxi) {
		if (taxi == null) {
			throw new RestServiceException("Bad Request", Response.Status.BAD_REQUEST);
		}
		try {
			Taxi addedTaxi= service.addTaxi(taxi);
			Common<Taxi> common=new Common<>("success",addedTaxi,1);
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
		}catch(UniqueRegistrationNumberException e) {
			String message = e.getMessage();
			Common<Customer> common=new Common<>("failure: "+message,null,0);
			return Response.ok(common).build();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new RestServiceException(e);
		}
		
		
	}
	
}
