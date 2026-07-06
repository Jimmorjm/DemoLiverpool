package com.test.liverpool.mapper;

import org.mapstruct.Mapper;

import com.test.liverpool.entity.UsuarioEntity;
import com.test.liverpool.requestdto.UsuarioRequestDto;
import com.test.liverpool.responsedto.UsuarioResponseDto;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	UsuarioRequestDto entityToRequestDto(UsuarioEntity entity);
    UsuarioEntity requestDtoToEntity(UsuarioRequestDto dto);
    
    UsuarioResponseDto entityToResponseDto(UsuarioEntity entity);
    UsuarioEntity responseDtoToEntity(UsuarioResponseDto dto);

}
