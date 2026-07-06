package com.test.liverpool.requestdto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductoRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	@NotBlank(message = "La clave del producto no puede ir vacia")
	private String clave;
	@NotBlank(message = "La descripcion no puede ir vacia")
	private String description;
	@NotNull(message = "El costo unitario no puede ir vacio")
	private Double costoUnitario;
	@NotNull(message = "La cantidad del Inventario no puede ir vacia")
	private Integer inventarioDisponible;
	@NotNull(message="Ingrese el DisplayName")
	private String displayName;

	public ProductoRequestDto() {
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

	public Integer getInventarioDisponible() {
		return inventarioDisponible;
	}

	public void setInventarioDisponible(Integer inventarioDisponible) {
		this.inventarioDisponible = inventarioDisponible;
	}

}
