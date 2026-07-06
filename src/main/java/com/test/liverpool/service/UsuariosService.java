package com.test.liverpool.service;

import com.test.liverpool.requestdto.UsuarioRequestDto;
import com.test.liverpool.responsedto.UsuarioResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuariosService {

	public Mono<UsuarioResponseDto> crearusuario(UsuarioRequestDto dto);

	public Flux<UsuarioResponseDto> findAllUSuarios();

	public Mono<UsuarioResponseDto> findByUserName(String usaerName);

	public Mono<UsuarioResponseDto> actualizaUsuario(UsuarioRequestDto dto);

	public Mono<UsuarioResponseDto> findById(Integer idUsuario);

}
