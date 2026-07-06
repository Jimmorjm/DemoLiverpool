package com.test.liverpool.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.test.liverpool.entity.UsuarioEntity;

import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository extends ReactiveCrudRepository<UsuarioEntity, Integer>{

	Mono<UsuarioEntity> findByUserName(String usarName);

	Mono<UsuarioEntity> findByCorreo(String correo);

}
