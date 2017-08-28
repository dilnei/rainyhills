package com.crx.rainyhills.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rainyhills.model.dto.SurfaceDTO;
import br.com.rainyhills.model.service.SurfaceServiceLocal;

/**
 * Controller class responsible for making the connection between the view and
 * the manageable beam.
 * 
 * We using <code>@Model</code> this is stereotype CDI to
 * <code>@RequestScope and @Named</code> Mor details in:
 * http://www.cdi-spec.org/faq/#accordion6
 * 
 * @author Dilnei Cunha
 */
@Model
public class SurfaceController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private SurfaceServiceLocal surfaceService;

	private SurfaceDTO surface;
	private List<SurfaceDTO> surfaces;

	/**
	 * Initialize some datas in view before class be in service but after it be constructed.
	 */
	@PostConstruct
	public void initSurface() {
		surface = new SurfaceDTO();
		retrieveAllSurfacesOrderedByValues();
	}

	/**
	 * Surface produces.
	 * 
	 * @return SurfaceDTO
	 */
	@Produces
	@Named
	public SurfaceDTO getSurface() {
		return surface;
	}

	/**
	 * Method responsible to invoc the service that persist this calculate in database.
	 * 
	 * @throws Exception
	 */
	public void register() throws Exception {
		try {
			surface.setVolume(surfaceService.calculatesUnit(surface.getValues())); 
			surfaceService.register(surface);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered! " + "Volume: " + surface.getVolume(), "Registration successful"));
			initSurface();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	/**
	 * @Named provides access the return value via the EL variable name "surfaces"
	 * in the UI (e.g., Facelets or JSP view)
	 * 
	 * @return List<SurfaceDTO>
	 */
	@Produces
	@Named
	public List<SurfaceDTO> getSurfaces() {
		return surfaces;
	}

	/**
	 * Patters observer responsible to update some data in view after this data be updated.
	 * 
	 * @param surface
	 */
	public void onSurfaceListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SurfaceDTO surface) {
		retrieveAllSurfacesOrderedByValues();
	}

	/**
	 * Method responsible to get all surfaces by service EJB
	 */
	private void retrieveAllSurfacesOrderedByValues() {
		surfaces = surfaceService.findAllOrderedByValues();
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}
}
