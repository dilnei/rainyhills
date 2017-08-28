package br.com.rainyhills.model.service;

import java.util.List;

import javax.ejb.Local;

import br.com.rainyhills.model.dto.SurfaceDTO;

/**
 * Interface EJB service local it contains the signature of service methods.
 * 
 * @author Dilnei Cunha
 */
@Local
public interface SurfaceServiceLocal {

	/**
	 * Signature of method responsible to register a surface.
	 * 
	 * @param surface
	 * @throws Exception
	 */
	public void register(SurfaceDTO surface) throws Exception;

	/**
	 * Signature of method responsible to find a surface by identifier.
	 * 
	 * @param id
	 * @return SurfaceDTO
	 */
	public SurfaceDTO findById(Long id);

	/**
	 * Signature of method responsible to return a list of surfaces that was
	 * performed.
	 * 
	 * @return List<SurfaceDTO>
	 */
	public List<SurfaceDTO> findAllOrderedByValues();

	/**
	 * Signature of method responsible to return a Integer value of volume.
	 * 
	 * @return Integer
	 */
	public Integer calculatesUnit(String values);
}
