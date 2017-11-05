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
import javax.ws.rs.QueryParam;
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
import uk.co.java.coursework.exception.UniqueEmailException;
import uk.co.java.coursework.service.CustomerServiceInterface;
import uk.co.java.coursework.utils.RestServiceException;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/customer", description = "Operations about test")
@Stateless
public class CustomerController {
	@Inject
	CustomerServiceInterface service;

	@GET
	@Cache
	@ApiOperation(value = "Fetch all Customers", notes = "return a JSON array of customer entities, if any")
	@Path("/getAllCustomers")
	public Response getAllCustomers() {
		List<Customer> all = service.getAllCustomers();
		Common<List<Customer>> common=new Common<>("success",all,1);
		return Response.ok(common).build();

	}

	@GET
	@ApiOperation(value = "Fetch a certain Customer by id", notes = "return a JSON object of customer entity, if exists")
	@Path("/getAllCustomerById/{id:[0-9]+}")
	public Response getAllCustomerById(@PathParam("id") Long id) {
		Customer customer = service.getCustomerById(id);
		Common<Customer> common=new Common<>("success",customer,1);
		
		return Response.ok(common).build();

	}

	@POST
	@ApiOperation(value = "Create Customer by adding whole variables except id", notes = "return a JSON object of customer entity, if succeed")
	@Path("/addCustomer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Contact created successfully."),
			@ApiResponse(code = 400, message = "Invalid Customer supplied in request body"),
			@ApiResponse(code = 200, message = "successfully add a customer"),
			@ApiResponse(code = 409, message = "Contact supplied in request body conflicts with an existing Contact"),
			@ApiResponse(code = 500, message = "An unexpected error occurred whilst processing the request") })

	public Response addCustomer(
			@ApiParam(value = "JSON representation of Customer object to be added to the database", required = true) Customer customer) {
		if (customer == null) {
			throw new RestServiceException("Bad Request", Response.Status.BAD_REQUEST);
		}

		try {
			Customer addedCustomer = service.createCustomer(customer);
			Common<Customer> common=new Common<>("success",addedCustomer,1);
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
		}catch(UniqueEmailException e) {
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
