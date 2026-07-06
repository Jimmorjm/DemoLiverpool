package com.test.liverpool.repository;

import java.util.List;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.test.liverpool.entity.PedidoProductoEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PedidoProductoRepository extends ReactiveCrudRepository<PedidoProductoEntity, Integer> {

	Flux<PedidoProductoEntity> findByIdPedido(Integer idPedido);

	
	@Query(value="UPDATE ENT_PEDIDO_PRODUCTO SET ESTATUS_PRODUCTO= :estatusProductoSend WHERE ID_PEDIDO = :idPedidoSend AND ID_PRODUCTO IN(:idsProductos)")
	Mono<Void> actualizaEstatusPedidoProductos(@Param("idPedidoSend") Integer idPedido, @Param("idsProductos") List<Integer> idsProductos, @Param("estatusProductoSend") Integer estatusProducto);


	@Query(value="SELECT pp.* FROM ENT_PEDIDO_PRODUCTO pp INNER JOIN ENT_PEDIDO p on p.ID_PEDIDO = pp.ID_PEDIDO WHERE p.ID_PEDIDO = :idPedidoSend AND pp.ESTATUS_PRODUCTO = :estatusProductos")
	Flux<PedidoProductoEntity> findByIdPedidoAndEstatus(@Param("idPedidoSend") Integer idPedido, @Param("estatusProductos") Integer estatusProductos);

}
