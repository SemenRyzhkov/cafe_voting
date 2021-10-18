package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.Dish;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface DishMapper {

    DishDto toDto(Dish dish);

    @InheritInverseConfiguration
    Dish toEntity(DishDto dto);

}
