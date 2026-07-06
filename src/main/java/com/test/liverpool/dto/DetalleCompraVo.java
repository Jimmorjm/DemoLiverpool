package com.test.rupper.vo;

import java.io.Serializable;
import java.util.List;

public class DetalleCompraVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCliente;
	private String nombreLista;
	private List<ProductoVo> lstProductos;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public List<ProductoVo> getLstProductos() {
		return lstProductos;
	}

	public void setLstProductos(List<ProductoVo> lstProductos) {
		this.lstProductos = lstProductos;
	}

}
