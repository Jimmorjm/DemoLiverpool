package com.test.liverpool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.liverpool.entity.DetalleUsuarioEntity;
import com.test.liverpool.entity.UsuarioEntity;
import com.test.liverpool.enums.EstatusDemoLiverpool;
import com.test.liverpool.mapper.DetalleUsuarioMapper;
import com.test.liverpool.mapper.UsuarioMapper;
import com.test.liverpool.repository.DetalleUsuarioRepository;
import com.test.liverpool.repository.PedidosRepository;
import com.test.liverpool.repository.UsuarioRepository;
import com.test.liverpool.requestdto.DetalleUsuarioRequestDto;
import com.test.liverpool.requestdto.UsuarioRequestDto;
import com.test.liverpool.responsedto.DetalleUsuarioResponseDto;
import com.test.liverpool.responsedto.UsuarioResponseDto;
import com.test.liverpool.service.UsuariosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuariosServiceImpl implements UsuariosService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private DetalleUsuarioRepository detalleUsuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;
	@Autowired
	private DetalleUsuarioMapper detalleUsuarioMapper;
	@Autowired
	private PedidosRepository pedioPedidosRepository;

	@Override
	public Mono<UsuarioResponseDto> crearusuario(UsuarioRequestDto dto) {
		if (dto.getLstDetalleUsuario() == null || dto.getLstDetalleUsuario().isEmpty()) {
			dto.setLstDetalleUsuario(new ArrayList<>());
		}

		return validaUsuario(dto).flatMap(msg -> Mono.just(new UsuarioResponseDto(msg)))
				.switchIfEmpty(Mono.defer(() -> {

					UsuarioEntity entity = usuarioMapper.requestDtoToEntity(dto);

					return usuarioRepository.save(entity).flatMap(usuario ->

				Flux.fromIterable(dto.getLstDetalleUsuario()).flatMap(detalleDto -> {
					DetalleUsuarioEntity detalleUsuario = detalleUsuarioMapper.requestDtoToEntity(detalleDto);
					detalleUsuario.setIdUsuario(usuario.getIdUsuario());
					detalleUsuario.setEstatusDetalleUsuario(EstatusDemoLiverpool.ESTATUS_ACTIVO.getIdEstatus());
					return detalleUsuarioRepository.save(detalleUsuario);
				}).then(Mono.just(usuarioMapper.entityToResponseDto(usuario))));

				}));
	}

	private Mono<String> validaUsuario(UsuarioRequestDto dto) {

		return usuarioRepository.findByUserName(dto.getUserName()).map(usuario -> "El User Name ya existe")
				.switchIfEmpty(
						usuarioRepository.findByCorreo(dto.getCorreo()).map(usuario -> "El correo enviado ya existe"));
	}

	@Override
	public Flux<UsuarioResponseDto> findAllUSuarios() {
		List<Integer> lstStatus = List.of(EstatusDemoLiverpool.ESTATUS_ACTIVO.getIdEstatus());
		return usuarioRepository.findAll().flatMap(user -> {
			UsuarioResponseDto dto = usuarioMapper.entityToResponseDto(user);
			Mono<List<DetalleUsuarioResponseDto>> detallesMono = detalleUsuarioRepository
					.findByIdUsuarioAndEstatus(user.getIdUsuario(), lstStatus)
					.map(detalleUsuarioMapper::entityToResponseDto).collectList();
			Mono<Integer> pedidosMono = pedioPedidosRepository.countPedidosRepacionadosUsuario(user.getIdUsuario());
			return Mono.zip(detallesMono, pedidosMono).map(tuple -> {
				dto.setLstDetalles(tuple.getT1());
				dto.setOrders(tuple.getT2());
				return dto;
			});
		});
	}

	@Override
	public Mono<UsuarioResponseDto> findByUserName(String usarName) {
		List<Integer> lstStatus = List.of(EstatusDemoLiverpool.ESTATUS_ACTIVO.getIdEstatus());
		return usuarioRepository.findByUserName(usarName).flatMap(usuario -> {
			UsuarioResponseDto dto = usuarioMapper.entityToResponseDto(usuario);
			Mono<List<DetalleUsuarioResponseDto>> detallesMono = detalleUsuarioRepository
					.findByIdUsuarioAndEstatus(usuario.getIdUsuario(), lstStatus)
					.map(detalleUsuarioMapper::entityToResponseDto).collectList();
			Mono<Integer> pedidosMono = pedioPedidosRepository.countPedidosRepacionadosUsuario(usuario.getIdUsuario());
			return Mono.zip(detallesMono, pedidosMono).map(tuple -> {
				dto.setLstDetalles(tuple.getT1());
				dto.setOrders(tuple.getT2());
				return dto;
			});
		}).onErrorResume(e -> Mono.just(new UsuarioResponseDto("Usuario no encontrado")));
	}

	@Override
	public Mono<UsuarioResponseDto> actualizaUsuario(UsuarioRequestDto dto) {

		Mono<Void> validaMono = Mono.empty();

		if (dto.getLstDetalleUsuario() != null && !dto.getLstDetalleUsuario().isEmpty()) {

			List<Integer> lstStatus = List.of(EstatusDemoLiverpool.ESTATUS_ACTIVO.getIdEstatus());

			validaMono = detalleUsuarioRepository.findByIdUsuarioAndEstatus(dto.getIdUsuario(), lstStatus).collectList()
					.flatMap(lst -> {
						for (DetalleUsuarioRequestDto detalle : dto.getLstDetalleUsuario()) {
							String msgError = validaDetalleUsuario(lst, detalle);
							if (msgError != null && !msgError.isEmpty()) {
								return Mono.error(new RuntimeException(msgError));
							}
						}
						return Mono.empty();
					});
		}

		return validaMono.then(usuarioRepository.findById(dto.getIdUsuario())).flatMap(usuario -> {
			usuario.setNombre(dto.getNombre());
			usuario.setApellidoMaterno(dto.getApellidoMaterno());
			usuario.setApellidoPaterno(dto.getApellidoPaterno());

			return usuarioRepository.save(usuario);
		}).flatMap(entity -> {

			Mono<Void> guardarDetalle = Mono.empty();
			if (dto.getLstDetalleUsuario() != null && !dto.getLstDetalleUsuario().isEmpty()) {
				guardarDetalle = guardaDetalleUsuario(dto.getLstDetalleUsuario(), entity.getIdUsuario());
			}

			Mono<Void> eliminarDetalle = Mono.empty();
			if (dto.getLstDetalleUsuarioDelete() != null && !dto.getLstDetalleUsuarioDelete().isEmpty()) {
				eliminarDetalle = actualizaEstatusDetalleUsuario(dto.getLstDetalleUsuarioDelete(),
						EstatusDemoLiverpool.ESTATUS_INACTIVO.getIdEstatus());
			}

			return guardarDetalle.then(eliminarDetalle).thenReturn(usuarioMapper.entityToResponseDto(entity));
		}).onErrorResume(e -> Mono.just(new UsuarioResponseDto(e.getMessage())));
	}

	private String validaDetalleUsuario(List<DetalleUsuarioEntity> lst, DetalleUsuarioRequestDto detalleNuevo) {
		if (lst != null && !lst.isEmpty()) {

			List<String> lstDirecciones = lst.stream()
					.map(detalle -> detalle.getCalleNumero() + ", " + detalle.getColoniaMunicipio() + ", "
							+ detalle.getDelAlcaldia() + ", " + detalle.getCodigoPostal() + ", " + detalle.getEstado())
					.toList();

			String nuevaDi = detalleNuevo.getCalleNumero() + ", " + detalleNuevo.getColoniaMunicipio() + ", "
					+ detalleNuevo.getDelAlcaldia() + ", " + detalleNuevo.getCodigoPostal() + ", "
					+ detalleNuevo.getEstado();

			if (lstDirecciones.stream().map(String::toUpperCase).anyMatch(nuevaDi.toUpperCase()::equals)) {

				return "La direccion " + detalleNuevo.getCalleNumero() + "," + detalleNuevo.getCodigoPostal()
						+ " ya la tiene el usuario";
			}
		}

		return "";
	}

	private Mono<Void> guardaDetalleUsuario(List<DetalleUsuarioRequestDto> lstDetalle, Integer idUsuario) {

		return Flux.fromIterable(lstDetalle).map(detalleUsuarioMapper::requestDtoToEntity).doOnNext(detalle -> {
			detalle.setIdUsuario(idUsuario);
			detalle.setEstatusDetalleUsuario(EstatusDemoLiverpool.ESTATUS_ACTIVO.getIdEstatus());
		}).flatMap(detalleUsuarioRepository::save).then();
	}

	private Mono<Void> actualizaEstatusDetalleUsuario(List<Integer> lstDetalle, Integer estatusDetalle) {

		return Flux.fromIterable(lstDetalle).flatMap(id -> detalleUsuarioRepository.findById(id).flatMap(detalle -> {
			detalle.setEstatusDetalleUsuario(estatusDetalle);
			return detalleUsuarioRepository.save(detalle);
		})).then();
	}

	@Override
	public Mono<UsuarioResponseDto> findById(Integer idUsuario) {
		List<Integer> lstStatus = List.of(EstatusDemoLiverpool.ESTATUS_ACTIVO.getIdEstatus());
		return usuarioRepository.findById(idUsuario).flatMap(usuario -> {
			UsuarioResponseDto dto = usuarioMapper.entityToResponseDto(usuario);
			Mono<List<DetalleUsuarioResponseDto>> detallesMono = detalleUsuarioRepository
					.findByIdUsuarioAndEstatus(usuario.getIdUsuario(), lstStatus)
					.map(detalleUsuarioMapper::entityToResponseDto).collectList();
			Mono<Integer> pedidosMono = pedioPedidosRepository.countPedidosRepacionadosUsuario(usuario.getIdUsuario());
			return Mono.zip(detallesMono, pedidosMono).map(tuple -> {
				dto.setLstDetalles(tuple.getT1());
				dto.setOrders(tuple.getT2());
				return dto;
			});
		}).onErrorResume(e -> Mono.just(new UsuarioResponseDto("Usuario no encontrado")));
	}
}
