package com.test.liverpool.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ENT_PEDIDO")
public class PedidoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column("ID_PEDIDO")
	private Integer idPedido;
	@Column("CANAL_VENTA")
	private String canalVenta;
	@Column("ESTATUS_PEDIDO")
	private Integer estatusPedido;
	@Column("TOTAL_PEDIDO")
	private Double totalPedido;
	@Column("ID_DETALLE_USUARIO")
	private Integer idDetalleUsuario;

	public PedidoEntity() {
	}

	public PedidoEntity(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public Integer getIdDetalleUsuario() {
		return idDetalleUsuario;
	}

	public void setIdDetalleUsuario(Integer idDetalleUsuario) {
		this.idDetalleUsuario = idDetalleUsuario;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getCanalVenta() {
		return canalVenta;
	}

	public void setCanalVenta(String canalVenta) {
		this.canalVenta = canalVenta;
	}

	public Integer getEstatusPedido() {
		return estatusPedido;
	}

	public void setEstatusPedido(Integer estatusPedido) {
		this.estatusPedido = estatusPedido;
	}

	public Double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}

}
