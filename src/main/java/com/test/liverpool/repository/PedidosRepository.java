package com.test.liverpool.repository;

import java.util.List;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.test.liverpool.entity.PedidoEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PedidosRepository extends ReactiveCrudRepository<PedidoEntity, Integer> {

	@Query(value = "SELECT p.* FROM ENT_PEDIDO p INNER JOIN ENT_DETALLE_USUARIO du on du.ID_DETALLE_USUARIO = p.ID_DETALLE_USUARIO INNER JOIN ENT_USUARIOS u ON u.ID_USUARIO= du.ID_USUARIO WHERE u.ID_USUARIO = :idUsuarioParam")
	Flux<PedidoEntity> findByIdUsuario(@Param("idUsuarioParam") Integer idUsuario);

	@Query(value = "SELECT p.* FROM ENT_PEDIDO p WHERE p.ESTATUS_PEDIDO in(:estatusPedidoSend) ")
	Flux<PedidoEntity> buscaPorEstatus(@Param("estatusPedidoSend") List<Integer> idsEstatus);

	@Query("SELECT count(p.ID_PEDIDO) FROM ENT_PEDIDO p INNER JOIN ENT_DETALLE_USUARIO du on du.ID_DETALLE_USUARIO=p.ID_DETALLE_USUARIO INNER JOIN ENT_USUARIOS u on u.ID_USUARIO= du.ID_USUARIO WHERE u.ID_USUARIO = :idUsuarioFind")
	Mono<Integer> countPedidosRepacionadosUsuario(@Param("idUsuarioFind") Integer idUsuario);

}
