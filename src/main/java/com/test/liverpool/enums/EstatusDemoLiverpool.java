package com.test.liverpool.enums;

public enum EstatusDemoLiverpool {

	ESTATUS_PEDIDO_PROCESO(1, "PROCESO"), ESTATUS_PEDIDO_CANCELADO(2, "CANCELADO"), ESTATUS_PEDIDO_COMPLETADO(3, "COMPLETADO"),
	PEDIDO_PRODUCTO_AGREGADO(1, "AGREGADO"), PEDIDO_PRODUCTO_REMOVIDO(2, "REMOVIDO"),
	ESTATUS_ACTIVO(1,"ACTIVO"), ESTATUS_INACTIVO(2,"INACTIVO");
	
	
	private Integer idEstatus;
	private String nombre;
	
	
	EstatusDemoLiverpool(Integer idEstatus, String nombre) {
		this.idEstatus = idEstatus;
		this.nombre = nombre;
	}


	public Integer getIdEstatus() {
		return idEstatus;
	}


	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public static EstatusDemoLiverpool getEstatusPedido(Integer estatusPedido) {
		if(estatusPedido.equals(ESTATUS_PEDIDO_PROCESO.getIdEstatus())) {
			return ESTATUS_PEDIDO_PROCESO;
		}else if(estatusPedido.equals(ESTATUS_PEDIDO_CANCELADO.getIdEstatus())) {
			return ESTATUS_PEDIDO_CANCELADO;
		}else if(estatusPedido.equals(ESTATUS_PEDIDO_COMPLETADO.getIdEstatus())) {
			return ESTATUS_PEDIDO_COMPLETADO;
		}
		return null;
	}
	
	
	
	

}
