package com.test.liverpool.mapper;

import org.mapstruct.Mapper;

import com.test.liverpool.entity.PedidoProductoEntity;
import com.test.liverpool.requestdto.PedidoProductoRequestDto;
import com.test.liverpool.responsedto.PedidoProductoResponseDto;

@Mapper(componentModel = "spring")
public interface PedidoProductoMapper {
	
	PedidoProductoRequestDto entityToRequestDto(PedidoProductoEntity entity);
	PedidoProductoEntity requestDtoToEntity(PedidoProductoRequestDto dto);
    
	PedidoProductoResponseDto entityToResponseDto(PedidoProductoEntity entity);
	PedidoProductoEntity responseDtoToEntity(PedidoProductoResponseDto dto);

}
