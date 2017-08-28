package com.crx.rainyhills.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * JSF Context Instance Producer Class.
 * 
 * @author Dilnei Cunha
 */
public class WebResources {

	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
