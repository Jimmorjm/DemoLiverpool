package com.test.liverpool.responsedto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DetalleUsuarioResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idDetalleUsuario;
	private String calleNumero;
	private String coloniaMunicipio;
	private String delAlcaldia;
	private String codigoPostal;
	private String estado;
	private Integer idUsuario;
	private UsuarioResponseDto usuario;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

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

	public UsuarioResponseDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResponseDto usuario) {
		this.usuario = usuario;
	}

}
