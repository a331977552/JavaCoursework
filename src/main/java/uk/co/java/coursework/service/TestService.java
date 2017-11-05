package uk.co.java.coursework.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import uk.co.java.coursework.dao.TestDao;
import uk.co.java.coursework.entity.Test;

@Dependent
public class TestService {


	@Inject
	TestDao dao;
	
	ResteasyClient client;
		
	public TestService() {
		client=new ResteasyClientBuilder().build();
	}
	public Test getTestById(long id) {
		
		
	return 	dao.findById(id);
	}
	public List<Test> getAllTest() {
		return dao.getAllTest();
	}
}
