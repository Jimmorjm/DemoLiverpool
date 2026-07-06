package com.test.liverpool.requestdto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class DetalleUsuarioRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idDetalleUsuario;
	@NotBlank(message = "La calle y numero no pueden ir vacios")
	private String calleNumero;
	@NotBlank(message = "La Colonia no puede ir vacia")
	private String coloniaMunicipio;
	@NotBlank(message = "La Alcaldia no puede ir vacia")
	private String delAlcaldia;
	@NotBlank(message = "El Codigo Postal no puede ir vacio")
	private String codigoPostal;
	@NotBlank(message = "El Estado no puede ir vacio")
	private String estado;
	@NotBlank(message = "El usuario no puede ir vacio")
	private Integer idUsuario;

	public Integer getIdDetalleUsuario() {
		return idDetalleUsuario;
	}

	public void setIdDetalleUsuario(Integer idDetalleUsuario) {
		this.idDetalleUsuario = idDetalleUsuario;
	}

	public String getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(String calleNumero) {
		this.calleNumero = calleNumero;
	}

	public String getColoniaMunicipio() {
		return coloniaMunicipio;
	}

	public void setColoniaMunicipio(String coloniaMunicipio) {
		this.coloniaMunicipio = coloniaMunicipio;
	}

	public String getDelAlcaldia() {
		return delAlcaldia;
	}

	public void setDelAlcaldia(String delAlcaldia) {
		this.delAlcaldia = delAlcaldia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	

}
