package com.crx.rainyhills.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * CDI resources class creates instances of Entity Manager.
 * 
 * @author Dilnei Cunha
 */
public class Resources {

	/**
	 * Produces Entity Manager.
	 */
	@Produces
	@PersistenceContext
	private EntityManager em;

	/**
	 * Produces Logger
	 * 
	 * @param injectionPoint
	 * @return Logger
	 */
	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
