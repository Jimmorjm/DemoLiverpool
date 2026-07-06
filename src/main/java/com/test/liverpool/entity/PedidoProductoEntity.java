package com.test.liverpool.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ENT_PEDIDO_PRODUCTO")
public class PedidoProductoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column("ID_PEDIDO_PRODUCTO")
	private Integer idPedidoProducto;
	@Column("ID_PEDIDO")
	private Integer idPedido;
	@Column("ID_PRODUCTO")
	private Integer idProducto;
	@Column("CANTIDAD_PIEZAS")
	private Integer cantidadPiezas;
	@Column("ESTATUS_PRODUCTO")
	private Integer estatusProducto;

	public Integer getEstatusProducto() {
		return estatusProducto;
	}

	public void setEstatusProducto(Integer estatusProducto) {
		this.estatusProducto = estatusProducto;
	}

	public Integer getCantidadPiezas() {
		return cantidadPiezas;
	}

	public void setCantidadPiezas(Integer cantidadPiezas) {
		this.cantidadPiezas = cantidadPiezas;
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

}
