package com.test.liverpool.responsedto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductoResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private String clave;
	private String description;
	private Double costoUnitario;
	private String displayName;
	private Integer inventarioDisponible;

	private String msgError;

	public ProductoResponseDto() {

	}

	public Integer getInventarioDisponible() {
		return inventarioDisponible;
	}

	public void setInventarioDisponible(Integer inventarioDisponible) {
		this.inventarioDisponible = inventarioDisponible;
	}

	public ProductoResponseDto(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

}
