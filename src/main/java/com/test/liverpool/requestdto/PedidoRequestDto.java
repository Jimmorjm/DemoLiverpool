package com.test.liverpool.requestdto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PedidoRequestDto {

	private Integer idPedido;
	@NotBlank(message = "El Canal de Venta no puede ir vacio")
	private String canalVenta;
	private Integer estatudPedido;
	private String nombreEstatusPedido;
	@NotNull(message = "El Total del pedido no puede ir vacio")
	private Double totalPedido;
	@NotNull(message = "Se debe seleccionar una direccion de entrega")
	private Integer idDetalleUsuario;
	@NotEmpty(message = "Se deben de agregar productos al pedido")
	private List<PedidoProductoRequestDto> productos;
	private List<Integer> productosEliminar;

	public PedidoRequestDto() {
	}

	public List<PedidoProductoRequestDto> getProductos() {
		return productos;
	}

	public void setProductos(List<PedidoProductoRequestDto> productos) {
		this.productos = productos;
	}

	public String getNombreEstatusPedido() {
		return nombreEstatusPedido;
	}

	public void setNombreEstatusPedido(String nombreEstatusPedido) {
		this.nombreEstatusPedido = nombreEstatusPedido;
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

	public Integer getEstatudPedido() {
		return estatudPedido;
	}

	public void setEstatudPedido(Integer estatudPedido) {
		this.estatudPedido = estatudPedido;
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

	public List<Integer> getProductosEliminar() {
		return productosEliminar;
	}

	public void setProductosEliminar(List<Integer> productosEliminar) {
		this.productosEliminar = productosEliminar;
	}

}
