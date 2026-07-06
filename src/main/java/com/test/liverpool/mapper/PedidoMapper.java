package com.test.liverpool.mapper;

import org.mapstruct.Mapper;

import com.test.liverpool.entity.PedidoEntity;
import com.test.liverpool.requestdto.PedidoRequestDto;
import com.test.liverpool.responsedto.PedidoResponseDto;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

	PedidoRequestDto entityToRequestDto(PedidoEntity entity);

	PedidoEntity requestDtoToEntity(PedidoRequestDto dto);

	PedidoResponseDto entityToResponseDto(PedidoEntity entity);

	PedidoEntity responseDtoToEntity(PedidoResponseDto dto);

}
