package com.test.liverpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.liverpool.requestdto.ProductoRequestDto;
import com.test.liverpool.responsedto.ProductoResponseDto;
import com.test.liverpool.service.ProductosService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items")
public class ProductosController {

	@Autowired
	private ProductosService productosService;

	@GetMapping
	public Flux<ProductoResponseDto> getAllProducto() {
		return productosService.findAllProductos();
	}

	@PostMapping
	public Mono<ProductoResponseDto> createProducto(@Valid @RequestBody ProductoRequestDto dto) {
		return productosService.creaproducto(dto);
	}

	@GetMapping("/findById{productId}")
	public Mono<ProductoResponseDto> getUserById(@RequestParam("productId") Integer productId) {
		return productosService.findById(productId);

	}

	@GetMapping("/findByDisplayName{displayName}")
	public Flux<ProductoResponseDto> getUserById(@RequestParam("displayName") String displayName) {
		return productosService.buscaProductoPorDisplayName(displayName);
	}

	@PutMapping("/{productId}")
	public Mono<ProductoResponseDto> updateProducto(@PathVariable("productId") Integer productId,
			@RequestBody ProductoRequestDto dto) {
		return productosService.actualizaProducto(dto);
	}

}
