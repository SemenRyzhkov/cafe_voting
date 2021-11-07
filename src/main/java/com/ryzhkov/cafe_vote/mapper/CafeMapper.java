package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserDto;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CafeMapper {

    CafeDto toDto(Cafe cafe);

    @InheritInverseConfiguration
    Cafe toEntity(CafeDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromDto(CafeDto accountEditDto, @MappingTarget Cafe cafe);

    List<CafeDto> toDtoList(List<Cafe> cafes);

    @InheritInverseConfiguration
    List<Cafe> toCafeList(List<CafeDto> dtos);

}
