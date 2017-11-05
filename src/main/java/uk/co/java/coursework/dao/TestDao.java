package uk.co.java.coursework.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import uk.co.java.coursework.entity.Test;

public class TestDao {

	@Inject
	private @Named("logger") Logger log;

	@Inject
	private EntityManager em;

	public Test findById(Long id) {
		return em.find(Test.class, id);
	}

	public List<Test> getAllTest() {

		 TypedQuery<Test> query = em.createNamedQuery(Test.FIND_ALL, Test.class);
        return query.getResultList();
		
	}

}
