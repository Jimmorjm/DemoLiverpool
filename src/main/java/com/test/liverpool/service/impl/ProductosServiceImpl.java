package com.test.liverpool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.liverpool.entity.ProductoEntity;
import com.test.liverpool.enums.EstatusDemoLiverpool;
import com.test.liverpool.mapper.ProductosMapper;
import com.test.liverpool.repository.PedidoProductoRepository;
import com.test.liverpool.repository.ProductosRepository;
import com.test.liverpool.requestdto.ProductoRequestDto;
import com.test.liverpool.responsedto.ProductoResponseDto;
import com.test.liverpool.service.ProductosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductosServiceImpl implements ProductosService {

	@Autowired
	private ProductosRepository productosRepository;

	@Autowired
	private PedidoProductoRepository pedidoProductoRepository;

	@Autowired
	private ProductosMapper productosMapper;

	@Override
	public Mono<ProductoResponseDto> creaproducto(ProductoRequestDto dto) {
		 ProductoEntity producto = productosMapper.requestDtoToEntity(dto);

		    return productosRepository.findByClave(dto.getClave())
		            .flatMap(prodExis ->
		                    Mono.just(new ProductoResponseDto(
		                            "La clave enviada ya esta siendo usada por otro producto")))
		            .switchIfEmpty(
		                    productosRepository.save(producto)
		                            .map(productosMapper::entityToResponseDto)
		            );
	}

	@Override
	public Flux<ProductoResponseDto> findAllProductos() {
		return productosRepository.findProductosDisponibles()
				.switchIfEmpty(Flux.error(new Exception("No se encontraron elementos")))
				.flatMap(item -> Mono.just(productosMapper.entityToResponseDto(item)));
	}

	@Override
	public Mono<ProductoResponseDto> actualizaProducto(ProductoRequestDto dto) {
		ProductoEntity prod = productosMapper.requestDtoToEntity(dto);
		return productosRepository.save(prod).flatMap(porducto -> Mono.just(productosMapper.entityToResponseDto(prod)));
	}

	@Override
	public Flux<ProductoResponseDto> buscaProductoPorDisplayName(String displayName) {
		displayName = displayName.replaceAll("[^a-zA-Z0-9\\s]", "");
		return productosRepository.findByDisplayName(displayName)
				.switchIfEmpty(Flux.error(new Exception("No se encontraron elementos")))
				.flatMap(producto -> Mono.just(productosMapper.entityToResponseDto(producto)));
	}

	@Override
	public Flux<ProductoResponseDto> buscaPorPedido(Integer idPedido) {
		return pedidoProductoRepository
				.findByIdPedidoAndEstatus(idPedido, EstatusDemoLiverpool.PEDIDO_PRODUCTO_AGREGADO.getIdEstatus())
				.switchIfEmpty(Flux.error(new Exception("No se encontraron elementos"))).flatMap(pedidoProducto -> {
					return productosRepository.findById(pedidoProducto.getIdProducto()).flatMap(producto -> {
						ProductoResponseDto dto = productosMapper.entityToResponseDto(producto);
						dto.setInventarioDisponible(pedidoProducto.getCantidadPiezas());
						return Mono.just(dto);
					});
				});
	}

	@Override
	public Mono<ProductoResponseDto> findById(Integer productId) {
		return productosRepository.findById(productId)
				.flatMap(producto -> Mono.just(productosMapper.entityToResponseDto(producto)))
				.onErrorResume(e -> Mono.just(new ProductoResponseDto("Pedido no encontrado")));
	}

}
