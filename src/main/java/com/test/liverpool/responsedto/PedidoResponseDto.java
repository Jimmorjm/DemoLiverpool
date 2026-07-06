package com.test.liverpool.responsedto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude( JsonInclude.Include.NON_EMPTY)
public class PedidoResponseDto {

	private Integer idPedido;
	private String canalVenta;
	private Integer estatusPedido;
	private String nombreEstatusPedido;
	private Double totalPedido;
	private Integer idDetalleUsuario;
	private DetalleUsuarioResponseDto detalleUsuario;
	private List<PedidoProductoResponseDto> productos;

	private String msgError;

	public PedidoResponseDto() {
	}

	public PedidoResponseDto(String msgErro) {
		this.msgError = msgErro;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public DetalleUsuarioResponseDto getDetalleUsuario() {
		return detalleUsuario;
	}

	public void setDetalleUsuario(DetalleUsuarioResponseDto detalleUsuario) {
		this.detalleUsuario = detalleUsuario;
	}

	public String getNombreEstatusPedido() {
		return nombreEstatusPedido;
	}

	public void setNombreEstatusPedido(String nombreEstatusPedido) {
		this.nombreEstatusPedido = nombreEstatusPedido;
	}

	public List<PedidoProductoResponseDto> getProductos() {
		return productos;
	}

	public void setProductos(List<PedidoProductoResponseDto> productos) {
		this.productos = productos;
	}

	public Integer getIdPedido() {
		return idPedido;
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

	public Integer getIdDetalleUsuario() {
		return idDetalleUsuario;
	}

	public void setIdDetalleUsuario(Integer idDetalleUsuario) {
		this.idDetalleUsuario = idDetalleUsuario;
	}

}
