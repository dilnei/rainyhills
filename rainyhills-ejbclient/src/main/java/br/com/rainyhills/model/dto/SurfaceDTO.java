package br.com.rainyhills.model.dto;

/**
 * This is a object to data transfer of the patterns DTO, this object represents
 * the surface.
 * 
 * @author Dilnei Cunha
 */
public class SurfaceDTO {

	private Long id;
	private String values;
	private Integer volume;

	public SurfaceDTO() {
	}

	public SurfaceDTO(Long id, String values, Integer volume) {
		this.id = id;
		this.values = values;
		this.volume = volume;
	}

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
