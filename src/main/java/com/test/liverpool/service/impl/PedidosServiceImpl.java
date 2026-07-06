package com.test.liverpool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.liverpool.entity.DetalleUsuarioEntity;
import com.test.liverpool.entity.PedidoEntity;
import com.test.liverpool.entity.PedidoProductoEntity;
import com.test.liverpool.enums.EstatusDemoLiverpool;
import com.test.liverpool.filters.PedidosFilter;
import com.test.liverpool.mapper.PedidoMapper;
import com.test.liverpool.mapper.PedidoProductoMapper;
import com.test.liverpool.repository.DetalleUsuarioRepository;
import com.test.liverpool.repository.PedidoProductoRepository;
import com.test.liverpool.repository.PedidosRepository;
import com.test.liverpool.repository.ProductosRepository;
import com.test.liverpool.requestdto.PedidoProductoRequestDto;
import com.test.liverpool.requestdto.PedidoRequestDto;
import com.test.liverpool.responsedto.PedidoProductoResponseDto;
import com.test.liverpool.responsedto.PedidoResponseDto;
import com.test.liverpool.service.PedidosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PedidosServiceImpl implements PedidosService {

	@Autowired
	private PedidosRepository pedidosRepository;
	@Autowired
	private PedidoProductoRepository pedidoProductoRepository;

	@Autowired
	private DetalleUsuarioRepository detalleUsuarioRepository;
	@Autowired
	private ProductosRepository productosRepository;

	@Autowired
	private PedidoMapper pedidoMapper;
	@Autowired
	private PedidoProductoMapper pedidoProductoMapper;

	@Override
	public Flux<PedidoResponseDto> buscaPedidosPorUsario(Integer idUsuario) {
		return pedidosRepository.findByIdUsuario(idUsuario)
				.flatMap(
						pedido -> pedidoProductoRepository.findByIdPedido(pedido.getIdPedido())
								.flatMap(pediProd -> productosRepository
										.getDisplayNameProductoByIdProduct(pediProd.getIdProducto())
										.map(displayName -> {
											PedidoProductoResponseDto pedPr = pedidoProductoMapper
													.entityToResponseDto(pediProd);
											pedPr.setDisplayName(displayName);
											return pedPr;
										}))
								.collectList().map(productos -> {
									PedidoResponseDto dto = pedidoMapper.entityToResponseDto(pedido);
									dto.setEstatusPedido(pedido.getEstatusPedido());
									dto.setProductos(productos);
									return dto;
								}));
	}

	@Override
	public Mono<PedidoResponseDto> creaPedido(PedidoRequestDto dto) {
		PedidoEntity pedidoEntity = pedidoMapper.requestDtoToEntity(dto);
		pedidoEntity.setEstatusPedido(EstatusDemoLiverpool.ESTATUS_PEDIDO_PROCESO.getIdEstatus());

		return pedidosRepository.save(pedidoEntity).flatMap(pedido ->

		Flux.fromIterable(dto.getProductos()).flatMap(prod -> {
			PedidoProductoEntity pedProd = new PedidoProductoEntity();
			pedProd.setIdPedido(pedido.getIdPedido());
			pedProd.setIdProducto(prod.getIdProducto());
			pedProd.setCantidadPiezas(prod.getCantidadPiezas());
			pedProd.setEstatusProducto(EstatusDemoLiverpool.PEDIDO_PRODUCTO_AGREGADO.getIdEstatus());

			return pedidoProductoRepository.save(pedProd);
		}).then(Mono.just(pedidoMapper.entityToResponseDto(pedido))));
	}

	@Override
	public Mono<PedidoResponseDto> modificaPedido(PedidoRequestDto dto) {

		return pedidosRepository.findById(dto.getIdPedido())
				.switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))

				.flatMap(pedidoOriginal ->

				detalleUsuarioRepository.findById(pedidoOriginal.getIdDetalleUsuario())
						.switchIfEmpty(Mono.error(new RuntimeException("Detalle usuario no encontrado")))

						.flatMap(detalle ->

						validaDirecciones(dto, Mono.just(detalle)).flatMap(error -> {

							if (error != null && !error.isEmpty()) {
								PedidoResponseDto resp = new PedidoResponseDto();
								resp.setMsgError(error);
								return Mono.just(resp);
							}

							PedidoEntity pedido = pedidoMapper.requestDtoToEntity(dto);

							if (pedido == null) {
								return Mono.error(new IllegalStateException("Mapper devolvió null"));
							}

							Mono<Void> operacionesProductos = Mono.empty();

							if (dto.getProductos() != null && !dto.getProductos().isEmpty()) {
								operacionesProductos = guardaPedidoProductos(dto.getProductos(), pedido.getIdPedido());
							}

							if (dto.getProductosEliminar() != null && !dto.getProductosEliminar().isEmpty()) {
								operacionesProductos = operacionesProductos.then(actualizaEstatusPedidosProductos(
										dto.getProductosEliminar(), pedido.getIdPedido(),
										EstatusDemoLiverpool.PEDIDO_PRODUCTO_REMOVIDO.getIdEstatus()));
							}

							PedidoResponseDto respuesta = pedidoMapper.entityToResponseDto(pedido);

							return operacionesProductos.then(pedidosRepository.save(pedido)).thenReturn(respuesta);
						})));
	}

	public Mono<String> validaDirecciones(PedidoRequestDto dto, Mono<DetalleUsuarioEntity> detalle) {

		List<Integer> estatus = List.of(EstatusDemoLiverpool.ESTATUS_ACTIVO.getIdEstatus());

		return detalle.flatMap(detalleEntity ->

		detalleUsuarioRepository.findByIdUsuarioAndEstatus(detalleEntity.getIdUsuario(), estatus)
				.map(DetalleUsuarioEntity::getIdDetalleUsuario).collectList().map(ids -> {

					if (!ids.contains(dto.getIdDetalleUsuario())) {
						return "La direccion seleccionada no pertenece al usuario";
					}

					return "";
				}));
	}

	private Mono<Void> actualizaEstatusPedidosProductos(List<Integer> productos, Integer idPedido,
			Integer idEstatusProducto) {
		return pedidoProductoRepository.actualizaEstatusPedidoProductos(idPedido, productos, idEstatusProducto);
	}

	private Mono<Void> guardaPedidoProductos(List<PedidoProductoRequestDto> productos, Integer idPedido) {
		return Flux.fromIterable(productos).flatMap(prod -> {

			PedidoProductoEntity entity = new PedidoProductoEntity();
			entity.setIdPedido(idPedido);
			entity.setIdProducto(prod.getIdProducto());
			entity.setCantidadPiezas(prod.getCantidadPiezas());
			entity.setEstatusProducto(EstatusDemoLiverpool.PEDIDO_PRODUCTO_AGREGADO.getIdEstatus());

			return pedidoProductoRepository.save(entity);
		}).then();
	}

	@Override
	public Mono<Void> cambiaEstatusPedido(Integer idPedido, Integer idEstatusPedido) {
	    return pedidosRepository.findById(idPedido)
	        .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
	        .flatMap(pedido -> {
	            pedido.setEstatusPedido(idEstatusPedido);
	            return pedidosRepository.save(pedido);
	        })
	        .then();
	}

	@Override
	public Flux<PedidoResponseDto> getAllPedidos() {
		List<Integer> lstStatusPedido = List.of(EstatusDemoLiverpool.ESTATUS_PEDIDO_PROCESO.getIdEstatus());

		return pedidosRepository.buscaPorEstatus(lstStatusPedido)
				.flatMap(pedido -> pedidoProductoRepository.findByIdPedido(pedido.getIdPedido())

						.flatMap(pediProd ->

						productosRepository.getDisplayNameProductoByIdProduct(pediProd.getIdProducto())

								.map(displayName -> {
									PedidoProductoResponseDto pedPr = pedidoProductoMapper
											.entityToResponseDto(pediProd);

									pedPr.setDisplayName(displayName);

									return pedPr;
								}))

						.collectList()

						.map(productos -> {

							PedidoResponseDto dto = pedidoMapper.entityToResponseDto(pedido);

							dto.setEstatusPedido(pedido.getEstatusPedido());
							dto.setProductos(productos);

							return dto;
						}));
	}

	@Override
	public Mono<PedidoResponseDto> findById(Integer id) {
		return pedidosRepository.findById(id).switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
				.flatMap(pe -> {

					PedidoResponseDto dto = pedidoMapper.entityToResponseDto(pe);

					return pedidoProductoRepository
							.findByIdPedidoAndEstatus(id, EstatusDemoLiverpool.PEDIDO_PRODUCTO_AGREGADO.getIdEstatus())
							.flatMap(pedProd -> productosRepository
									.getDisplayNameProductoByIdProduct(pedProd.getIdProducto()).map(displayName -> {
										PedidoProductoResponseDto pedPr = pedidoProductoMapper
												.entityToResponseDto(pedProd);

										pedPr.setDisplayName(displayName);
										return pedPr;
									}))
							.collectList().map(lista -> {
								dto.setProductos(lista);
								return dto;
							});
				}).onErrorResume(e -> Mono.just(new PedidoResponseDto("Pedido no encontrado")));

	}

	@Override
	public Flux<PedidoResponseDto> findByFilters(PedidosFilter pedidosFilter) {
		Flux<PedidoEntity> lst = pedidosRepository.findAll()
				.filter(pedido -> pedidosFilter.getEstatusPedido() == null
						|| pedidosFilter.getEstatusPedido().equals(0)
						|| pedido.getEstatusPedido().equals(pedidosFilter.getEstatusPedido()))
				.filter(pedido -> pedidosFilter.getTotalPedido() == null
						|| pedidosFilter.getTotalPedido().equals(0)
						|| pedido.getTotalPedido() <= pedidosFilter.getTotalPedido())
				.filter(pedido -> pedidosFilter.getCanalVenta() == null
						|| pedidosFilter.getCanalVenta().isBlank()
						|| pedido.getCanalVenta().equalsIgnoreCase(pedidosFilter.getCanalVenta()));

		return lst.flatMap(pedido -> {
			PedidoResponseDto dto = pedidoMapper.entityToResponseDto(pedido);
			return pedidoProductoRepository
					.findByIdPedidoAndEstatus(pedido.getIdPedido(), EstatusDemoLiverpool.PEDIDO_PRODUCTO_AGREGADO.getIdEstatus())
					.flatMap(pedProd -> productosRepository
							.getDisplayNameProductoByIdProduct(pedProd.getIdProducto()).map(displayName -> {
								PedidoProductoResponseDto pedPr = pedidoProductoMapper
										.entityToResponseDto(pedProd);

								pedPr.setDisplayName(displayName);
								return pedPr;
							}))
					.collectList().map(lista -> {
						dto.setProductos(lista);
						return dto;
					});
		});
	}

}
