package com.test.rupper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.rupper.service.CompraService;
import com.test.rupper.vo.DetalleCompraVo;

@RestController
@RequestMapping(path = "/compras")
public class ComprasController {

	@Autowired
	CompraService compraService;

	@PostMapping(path = "/saveAllCompras")
	public String saveAllCompras(@RequestBody List<DetalleCompraVo> lstCompraDetalle) {
		return compraService.saveAllCompras(lstCompraDetalle);
	}

	@GetMapping(path = "/getByIdCliente/{idCliente}")
	public List<DetalleCompraVo> getByIdCliente(@PathVariable("idCliente") Integer idCliente) {
		return compraService.getByIdCliente(idCliente);
	}

	@PutMapping(path = "/updateDatelleCompra")
	public String updateDatelleCompra(@RequestBody List<DetalleCompraVo> lstUpdate) {
		return compraService.updateDatelleCompra(lstUpdate);
	}

	@DeleteMapping(path = "/deleteCompraBycliente")
	public String deleteCompraBycliente(@RequestBody DetalleCompraVo objDelete) {
		return compraService.deleteCompraBycliente(objDelete);
	}

}
