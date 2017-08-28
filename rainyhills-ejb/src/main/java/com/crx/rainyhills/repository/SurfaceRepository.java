package com.crx.rainyhills.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.crx.rainyhills.entity.Surface;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class SurfaceRepository {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Surface findById(Long id) {
		return em.find(Surface.class, id);
	}

	/**
	 * Method that store a surface in the database.
	 */
	public void store(Surface surface) throws Exception {
		em.persist(surface);
	}

	/**
	 * Return a list of Surface by values.
	 * 
	 * @return List<Surface>
	 */
	public List<Surface> findAllOrderedByValues() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Surface> criteria = cb.createQuery(Surface.class);
		Root<Surface> surface = criteria.from(Surface.class);
		// Swap criteria statements if you would like to try out type-safe criteria
		// queries, a new
		// feature in JPA 2.0
		// criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		criteria.select(surface).orderBy(cb.asc(surface.get("values")));
		return em.createQuery(criteria).getResultList();
	}
}
