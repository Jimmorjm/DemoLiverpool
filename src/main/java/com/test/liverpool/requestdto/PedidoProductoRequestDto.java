package com.test.liverpool.requestdto;

public class PedidoProductoRequestDto {

	private Integer idPedidoProducto;
	private Integer idPedido;
	private Integer idProducto;
	private Integer cantidadPiezas;
	private Integer estatusProducto;

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
