package com.test.liverpool.mapper;

import org.mapstruct.Mapper;

import com.test.liverpool.entity.ProductoEntity;
import com.test.liverpool.requestdto.ProductoRequestDto;
import com.test.liverpool.responsedto.ProductoResponseDto;

@Mapper(componentModel = "spring")
public interface ProductosMapper {
	
	ProductoRequestDto entityToRequestDto(ProductoEntity entity);
	ProductoEntity requestDtoToEntity(ProductoRequestDto dto);
    
	ProductoResponseDto entityToResponseDto(ProductoEntity entity);
	ProductoEntity responseDtoToEntity(ProductoResponseDto dto);

}
