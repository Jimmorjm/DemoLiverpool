package com.test.liverpool.responsedto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude( JsonInclude.Include.NON_EMPTY)
public class PedidoProductoResponseDto {

	private Integer idPedidoProducto;
	private Integer idPedido;
	private Integer idProducto;
	private Integer cantidadPiezas;
	private Integer estatusProducto;
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getIdPedidoProducto() {
		return idPedidoProducto;
	}

	public void setIdPedidoProducto(Integer idPedidoProducto) {
		this.idPedidoProducto = idPedidoProducto;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidadPiezas() {
		return cantidadPiezas;
	}

	public void setCantidadPiezas(Integer cantidadPiezas) {
		this.cantidadPiezas = cantidadPiezas;
	}

	public Integer getEstatusProducto() {
		return estatusProducto;
	}

	public void setEstatusProducto(Integer estatusProducto) {
		this.estatusProducto = estatusProducto;
	}

}
