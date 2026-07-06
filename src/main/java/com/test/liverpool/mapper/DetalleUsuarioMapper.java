package com.test.liverpool.mapper;

import org.mapstruct.Mapper;

import com.test.liverpool.entity.DetalleUsuarioEntity;
import com.test.liverpool.requestdto.DetalleUsuarioRequestDto;
import com.test.liverpool.responsedto.DetalleUsuarioResponseDto;

@Mapper(componentModel = "spring")
public interface DetalleUsuarioMapper {

	
	DetalleUsuarioRequestDto entityToRequestDto(DetalleUsuarioEntity entity);
	DetalleUsuarioEntity requestDtoToEntity(DetalleUsuarioRequestDto dto);
    
	DetalleUsuarioResponseDto entityToResponseDto(DetalleUsuarioEntity entity);
    DetalleUsuarioEntity responseDtoToEntity(DetalleUsuarioResponseDto dto);
}
