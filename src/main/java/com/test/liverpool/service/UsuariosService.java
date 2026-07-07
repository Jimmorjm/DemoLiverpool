package com.test.liverpool.service;

import com.test.liverpool.requestdto.UsuarioRequestDto;
import com.test.liverpool.responsedto.UsuarioResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuariosService {

	Mono<UsuarioResponseDto> crearusuario(UsuarioRequestDto dto);

	Flux<UsuarioResponseDto> findAllUSuarios();

	Mono<UsuarioResponseDto> findByUserName(String usaerName);

	Mono<UsuarioResponseDto> actualizaUsuario(UsuarioRequestDto dto);

	Mono<UsuarioResponseDto> findById(Integer idUsuario);

}
