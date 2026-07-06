package com.test.liverpool.filters;

public class PedidosFilter {

	private Integer estatusPedido;
	private String canalVenta;
	private Integer idDetalleUsuario;
	private Double totalPedido;

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

	public Integer getIdDetalleUsuario() {
		return idDetalleUsuario;
	}

	public void setIdDetalleUsuario(Integer idDetalleUsuario) {
		this.idDetalleUsuario = idDetalleUsuario;
	}

}
