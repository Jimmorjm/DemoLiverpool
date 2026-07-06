package com.test.rupper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.rupper.model.Cliente;
import com.test.rupper.model.Compra;
import com.test.rupper.model.CompraDetalle;
import com.test.rupper.model.CompraDetallePK;
import com.test.rupper.model.Productos;
import com.test.rupper.repository.ClienteRepository;
import com.test.rupper.repository.CompraDetalleRepository;
import com.test.rupper.repository.CompraRepository;
import com.test.rupper.repository.ProductosRepository;
import com.test.rupper.service.CompraService;
import com.test.rupper.vo.DetalleCompraVo;
import com.test.rupper.vo.ProductoVo;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CompraDetalleRepository compraDetalleRepository;
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private ProductosRepository productosRepository;

	@Override
	public String saveAllCompras(List<DetalleCompraVo> lstCompraDetalle) {
		try {
			CompraDetalle compraDetalle = null;
			for (DetalleCompraVo detalleCompraVo : lstCompraDetalle) {
				Cliente cli = clienteRepository.findById(detalleCompraVo.getIdCliente()).orElse(null);
				Compra cmp = new Compra();
				cmp.setActivo(true);
				cmp.setCliente(cli);
				cmp.setFechaRegistro(new Date());
				cmp.setNombre(detalleCompraVo.getNombreLista());
				cmp = compraRepository.save(cmp);
				for (ProductoVo prodVo : detalleCompraVo.getLstProductos()) {
					Productos prod = productosRepository.findById(prodVo.getIdProducto()).orElse(null);
					compraDetalle = new CompraDetalle();
					compraDetalle.setDetalleCompraId(new CompraDetallePK(cmp.getIdCompra(), prod.getIdProducto()));
					compraDetalle.setCantidad(prodVo.getCantidad());
					compraDetalleRepository.save(compraDetalle);
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR EN LA OPERACION";
		}
	}

	@Override
	public List<DetalleCompraVo> getByIdCliente(Integer idCliente) {

		Cliente cli = clienteRepository.findById(idCliente).orElse(null);
		List<Compra> lstCompras = compraRepository.findByIdCliente(idCliente);

		List<DetalleCompraVo> lstVo = new ArrayList<>();
		DetalleCompraVo vo = null;

		for (Compra compra : lstCompras) {
			vo = new DetalleCompraVo();
			vo.setIdCliente(cli.getIdCliente());
			vo.setNombreLista(compra.getNombre());
			vo.setLstProductos(new ArrayList<>());
			ProductoVo prodVo = null;
			List<CompraDetalle> detallesLst = compraDetalleRepository.findByIdCompra(compra.getIdCompra());
			for (CompraDetalle compraDetalle : detallesLst) {
				prodVo = new ProductoVo();
				prodVo.setIdProducto(compraDetalle.getDetalleCompraId().getProductos().getIdProducto());
				prodVo.setCantidad(compraDetalle.getCantidad());
				vo.getLstProductos().add(prodVo);
			}
			lstVo.add(vo);
		}
		return lstVo;
	}

	@Override
	public String updateDatelleCompra(List<DetalleCompraVo> lstUpdate) {
		try {
			for (DetalleCompraVo detalleCompraVo : lstUpdate) {
				List<Compra> lstCompras = compraRepository.findByIdCliente(detalleCompraVo.getIdCliente());
				
				if(lstCompras== null || lstCompras.isEmpty()) {
					return "El cliente:"+detalleCompraVo.getIdCliente() +" no cuenta con alguna compra para poder actualizar";
				}
				for (Compra compra : lstCompras) {
					compra.setNombre("Lista Actualizada");
					compra.setFechaUltimaActualizacion(new Date());
					compraRepository.save(compra);
					for (ProductoVo prodVo : detalleCompraVo.getLstProductos()) {
						CompraDetalle cmpDet = new CompraDetalle();
						cmpDet.setCantidad(prodVo.getCantidad());
						cmpDet.setDetalleCompraId(new CompraDetallePK(compra.getIdCompra(), prodVo.getIdProducto()));
						compraDetalleRepository.save(cmpDet);
					}
				}
			}
			return null;
		} catch (Exception e) {
			return "ERROR EN LA OPERACION UPDATE LIST";
		}
	}

	@Override
	public String deleteCompraBycliente(DetalleCompraVo objDelete) {
		try {
			List<Compra> lstCcompras = compraRepository.findByNameAndIdCliente(objDelete.getNombreLista(), objDelete.getIdCliente());
			if(lstCcompras == null || lstCcompras.isEmpty()) {
				return "La lista: "+objDelete.getNombreLista()+" del Cliente:"+objDelete.getIdCliente()+" no se encotro para poder eliminar";
			}
			for (Compra compra : lstCcompras) {
				List<CompraDetalle> lstDet = compraDetalleRepository.findByIdCompra(compra.getIdCompra());
				compraDetalleRepository.deleteAll(lstDet);
				compraRepository.delete(compra);
			}
			return null;
		} catch (Exception e) {
			return "ERROR EN LA OPERACION DELETE";
		}
	}
}
