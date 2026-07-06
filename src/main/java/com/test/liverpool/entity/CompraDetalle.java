package com.test.rupper.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMPRA_DETALLE")
public class CompraDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CompraDetallePK detalleCompraId;
	@Column(name = "CANTIDAD")
	private Integer cantidad;

	public CompraDetallePK getDetalleCompraId() {
		return detalleCompraId;
	}

	public void setDetalleCompraId(CompraDetallePK detalleCompraId) {
		this.detalleCompraId = detalleCompraId;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
