package com.test.liverpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.test.liverpool.filters.PedidosFilter;
import com.test.liverpool.requestdto.PedidoRequestDto;
import com.test.liverpool.responsedto.PedidoResponseDto;
import com.test.liverpool.service.PedidosService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private PedidosService pedidosService;

	@GetMapping
	public Flux<PedidoResponseDto> getAllPedidos() {
		return pedidosService.getAllPedidos();
	}

	@PostMapping
	public Mono<PedidoResponseDto> createPedido(@Valid @RequestBody PedidoRequestDto dto) {
		return pedidosService.creaPedido(dto);
	}

	@GetMapping("/findByIdUsuario{userId}")
	public Flux<PedidoResponseDto> getPEdidosByUserName(@RequestParam("userId") Integer userId) {
		return pedidosService.buscaPedidosPorUsario(userId);
	}

	@GetMapping("/findById{idPedido}")
	public Mono<PedidoResponseDto> getUserById(@RequestParam("idPedido") Integer idPedido) {
		return pedidosService.findById(idPedido);

	}

	public Mono<PedidoResponseDto> validaResponse(PedidoResponseDto dto) {
		if (dto.getMsgError() != null) {
			return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, dto.getMsgError()));
		}
		return Mono.just(dto);
	}

	@PutMapping("/{id}")
	public Mono<PedidoResponseDto> updatePedido(@PathVariable("id") Integer idPedido,
			@RequestBody PedidoRequestDto dto) {
		dto.setIdPedido(idPedido);
		return pedidosService.modificaPedido(dto);
	}
	
	@GetMapping("/findByFilters")
	public Flux<PedidoResponseDto> findByFilters(@ModelAttribute PedidosFilter pedidosFilter) {
		return pedidosService.findByFilters(pedidosFilter);
	}

}
