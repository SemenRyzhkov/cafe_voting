package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.model.Cafe;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CafeMapper {

    CafeDto toDto(Cafe cafe);

    @InheritInverseConfiguration
    Cafe toEntity(CafeDto dto);

}
