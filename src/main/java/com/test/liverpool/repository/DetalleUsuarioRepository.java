package com.test.liverpool.repository;

import java.util.List;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.test.liverpool.entity.DetalleUsuarioEntity;

import reactor.core.publisher.Flux;

@Repository
public interface DetalleUsuarioRepository extends ReactiveCrudRepository<DetalleUsuarioEntity, Integer>{
	
	 @Query(value = " SELECT du.* FROM ENT_DETALLE_USUARIO du " +
	 				" WHERE du.ID_USUARIO = :idUsuarioSend "+
	 				" AND du.ESTATUS_DETALLE_USUARIO in (:lstIdsEstatus) ")
	 Flux<DetalleUsuarioEntity> findByIdUsuarioAndEstatus(@Param("idUsuarioSend") Integer idUsuario, @Param("lstIdsEstatus") List<Integer> lstIdsEstatus);

}
