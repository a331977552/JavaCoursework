package uk.co.java.coursework.web;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import uk.co.java.coursework.entity.Test;
import uk.co.java.coursework.service.TestService;

@Path("/test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/test", description = "Operations about test")
@Stateless
public class TestController {

	@Inject
	TestService service;
	
	@GET
	@ApiOperation(value = "just for test", notes = "return a test class entity")
	@Path("/test")
	public Response testA() {
	
		Test testById = service.getTestById(1);
		
		
		
		return 	Response.ok(testById).build();
		
		
	}
	
}
