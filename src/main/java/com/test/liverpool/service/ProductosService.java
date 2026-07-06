package com.test.liverpool.service;

import com.test.liverpool.requestdto.ProductoRequestDto;
import com.test.liverpool.responsedto.ProductoResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosService {
	
	Mono<ProductoResponseDto> creaproducto(ProductoRequestDto dto);
	
	Flux<ProductoResponseDto> findAllProductos();
	
	Mono<ProductoResponseDto> actualizaProducto(ProductoRequestDto dto);
	
	Flux<ProductoResponseDto> buscaProductoPorDisplayName(String displayName);
	
	Flux<ProductoResponseDto> buscaPorPedido(Integer idPedido);

	Mono<ProductoResponseDto> findById(Integer productId);
}
