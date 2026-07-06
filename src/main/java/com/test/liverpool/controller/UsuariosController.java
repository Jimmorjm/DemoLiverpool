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

import com.test.liverpool.requestdto.UsuarioRequestDto;
import com.test.liverpool.responsedto.UsuarioResponseDto;
import com.test.liverpool.service.UsuariosService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;

	@GetMapping
	public Flux<UsuarioResponseDto> getAllUsers() {
		return usuariosService.findAllUSuarios();
	}

	@PostMapping
	public Mono<UsuarioResponseDto> createUser(@Valid @RequestBody UsuarioRequestDto dto) {
		return usuariosService.crearusuario(dto);
	}

	@GetMapping("/findByUserName{userName}")
	public Mono<UsuarioResponseDto> getUserByUserName(@RequestParam("userName") String userName) {
		return usuariosService.findByUserName(userName);
	}

	@GetMapping("/findById{id}")
	public Mono<UsuarioResponseDto> getUserById(@RequestParam("id") Integer id) {
		return usuariosService.findById(id);

	}

	@PutMapping("/{id}")
	public Mono<UsuarioResponseDto> updateUser(@PathVariable("id") Integer userId, @RequestBody UsuarioRequestDto dto) {
		dto.setIdUsuario(userId);
		return usuariosService.actualizaUsuario(dto);
	}

}
