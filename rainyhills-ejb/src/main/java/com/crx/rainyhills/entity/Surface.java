package com.crx.rainyhills.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class that represents database table SURFACE and represents a fomart
 * JSON file.
 * 
 * @author Dilnei Cunha
 */
@Entity
@XmlRootElement
@Table(name = "SURFACE")
public class Surface implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identifier from entity.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Values that are the surface.
	 */
	@NotNull
	private String values;

	/**
	 * Volume of the surface.
	 */
	private Integer volume;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}
}
