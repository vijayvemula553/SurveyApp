package org.vijay.survey.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Named(value = "baseDaoImpl")
@Transactional
public class BaseDaoImpl {

	@PersistenceContext(unitName = "primaryDbEntityManagerFactory")
	public EntityManager em;

}
