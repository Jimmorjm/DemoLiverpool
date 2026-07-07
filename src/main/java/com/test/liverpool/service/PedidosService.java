package com.test.liverpool.service;

import com.test.liverpool.filters.PedidosFilter;
import com.test.liverpool.requestdto.PedidoRequestDto;
import com.test.liverpool.responsedto.PedidoResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PedidosService {

	Flux<PedidoResponseDto> buscaPedidosPorUsario(Integer idUsuario);

	Mono<PedidoResponseDto> creaPedido(PedidoRequestDto dto);

	Mono<PedidoResponseDto> modificaPedido(PedidoRequestDto dto);

	Mono<Void> cambiaEstatusPedido(Integer idPedido, Integer idEstatusPedido);

	Flux<PedidoResponseDto> getAllPedidos();

	Mono<PedidoResponseDto> findById(Integer id);

	Flux<PedidoResponseDto> findByFilters(PedidosFilter pedidosFilter);

}
