package com.test.rupper.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CompraDetallePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "ID_COMPRA")
	private Compra compra;
	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	private Productos productos;

	public CompraDetallePK() {

	}

	public CompraDetallePK(Integer idCompra, Integer idProductos) {
		this.compra = new Compra(idCompra);
		this.productos = new Productos(idProductos);
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

}
