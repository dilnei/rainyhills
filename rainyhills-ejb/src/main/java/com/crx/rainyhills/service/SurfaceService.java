package com.crx.rainyhills.service;

import com.crx.rainyhills.entity.Surface;
import com.crx.rainyhills.repository.SurfaceRepository;

import br.com.rainyhills.model.dto.SurfaceDTO;
import br.com.rainyhills.model.service.SurfaceServiceLocal;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * EJB business class contains the implementation of the local interface,
 * contains all of rules business of this service.
 * 
 * @author Dilnei Cunha
 */
@Stateless
public class SurfaceService implements SurfaceServiceLocal {

	@Inject
	private Logger log;

	@Inject
	private Event<Surface> surfaceEventSrc;

	@Inject
	private SurfaceRepository surfaceRepository;

	/**
	 * Method that implements the register of the surface.
	 */
	@Override
	public void register(SurfaceDTO surface) throws Exception {
		log.info("Registering " + surface.getValues());
		Surface surfaceDB = new Surface();
		surfaceDB.setValues(surface.getValues());
		surfaceDB.setVolume(surface.getVolume());
		surfaceRepository.store(surfaceDB);
		surfaceEventSrc.fire(surfaceDB);
	}

	/**
	 * Find a element surface by ID.
	 */
	@Override
	public SurfaceDTO findById(Long id) {
		Surface surface = surfaceRepository.findById(id);
		SurfaceDTO dto = new SurfaceDTO();
		dto.setId(surface.getId());
		dto.setValues(surface.getValues());
		dto.setVolume(surface.getVolume());
		return dto;
	}

	/**
	 * Get all registers by values.
	 */
	@Override
	public List<SurfaceDTO> findAllOrderedByValues() {
		List<Surface> surfaces = surfaceRepository.findAllOrderedByValues();
		List<SurfaceDTO> surfacesDto = new ArrayList<>();

		surfaces.stream().forEach(surface -> {
			SurfaceDTO dto = new SurfaceDTO();
			dto.setId(surface.getId());
			dto.setValues(surface.getValues());
			dto.setVolume(surface.getVolume());
			surfacesDto.add(dto);
		});
		return surfacesDto;
	}

	@Override
	public Integer calculatesUnit(String values) {

		int volume = 0;
		String valuesStr = values.trim();
		valuesStr = valuesStr.replaceAll("[^\\d.]", "");
		valuesStr = valuesStr.replace(".", "");

		List<Integer> valuesInt = Arrays.stream(valuesStr.split("\\B")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

		List<Integer> leftContainer = new ArrayList<>(valuesInt.size());
		List<Integer> rightContainer = new ArrayList<>(valuesInt.size());
		Collections.addAll(rightContainer, 0, 0, 0, 0, 0);

		leftContainer.add(0, valuesInt.get(0));

		for (int i = 1; i < valuesInt.size(); i++) {
			leftContainer.add(i, Math.max(leftContainer.get(i - 1).intValue(), valuesInt.get(i).intValue()));
		}

		rightContainer.add(valuesInt.size() - 1, valuesInt.get(valuesInt.size() - 1).intValue());

		for (int i = valuesInt.size() - 2; i >= 0; i--) {
			rightContainer.set(i, Math.max(rightContainer.get(i + 1).intValue(), valuesInt.get(i).intValue()));
		}

		for (int i = 0; i < valuesInt.size(); i++) {
			volume += Math.min(leftContainer.get(i).intValue(), rightContainer.get(i).intValue()) - valuesInt.get(i).intValue();
		}

		return volume;
	}
}
