package com.test.rupper.vo;

import java.io.Serializable;

public class ProductoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private Integer cantidad;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
