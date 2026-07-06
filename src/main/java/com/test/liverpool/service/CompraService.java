package com.test.rupper.service;

import java.util.List;

import com.test.rupper.vo.DetalleCompraVo;

public interface CompraService {

	public String saveAllCompras(List<DetalleCompraVo> lstCompraDetalle);

	public List<DetalleCompraVo> getByIdCliente(Integer idCliente);

	public String updateDatelleCompra(List<DetalleCompraVo> lstUpdate);
	
	
	public String deleteCompraBycliente(DetalleCompraVo objDelete);

}
