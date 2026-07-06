package com.test.liverpool.service;

import com.test.liverpool.filters.PedidosFilter;
import com.test.liverpool.requestdto.PedidoRequestDto;
import com.test.liverpool.responsedto.PedidoResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PedidosService {

	public Flux<PedidoResponseDto> buscaPedidosPorUsario(Integer idUsuario);

	public Mono<PedidoResponseDto> creaPedido(PedidoRequestDto dto);

	public Mono<PedidoResponseDto> modificaPedido(PedidoRequestDto dto);

	public Mono<Void> cambiaEstatusPedido(Integer idPedido, Integer idEstatusPedido);

	public Flux<PedidoResponseDto> getAllPedidos();

	public Mono<PedidoResponseDto> findById(Integer id);

	public Flux<PedidoResponseDto> findByFilters(PedidosFilter pedidosFilter);

}
