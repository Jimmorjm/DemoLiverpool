package com.test.liverpool.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.test.liverpool.entity.ProductoEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductosRepository extends ReactiveCrudRepository<ProductoEntity, Integer> {

	@Query(value = "SELECT p.* FROM CAT_PRODUCTO p WHERE p.INVENTARIO_DISPONIBLE > 0")
	Flux<ProductoEntity> findProductosDisponibles();

	@Query("""
			SELECT p.*
			FROM CAT_PRODUCTO p
			WHERE UPPER(p.DISPLAY_NAME) LIKE CONCAT('%', UPPER(:displayNameSend), '%')
			""")
	Flux<ProductoEntity> findByDisplayName(@Param("displayNameSend") String displayName);

	@Query("SELECT p.DISPLAY_NAME FROM CAT_PRODUCTO p WHERE p.ID_PRODUCTO = :idProducto")
	Mono<String> getDisplayNameProductoByIdProduct(@Param("idProducto") Integer idProducto);

	Mono<ProductoEntity> findByClave(String clave);

}
