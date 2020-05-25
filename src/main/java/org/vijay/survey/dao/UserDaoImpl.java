package org.vijay.survey.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.vijay.survey.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Named(value = "userDaoImpl")
@Transactional
public class UserDaoImpl {

	@PersistenceContext(unitName = "primaryDbEntityManagerFactory")
	private EntityManager em;

	public User saveOrUpdate(User entity) {
		User managedEntity = null;
		if (entity.getId() == null) {
			em.persist(entity);
			managedEntity = entity;
		} else {
			managedEntity = em.merge(entity);
		}
		return managedEntity;
	}
}
