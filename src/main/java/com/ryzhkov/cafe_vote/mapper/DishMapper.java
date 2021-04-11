package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DishMapper extends AbstractMapper<Dish, DishDto> {
    @Autowired
    public DishMapper() {
        super(Dish.class, DishDto.class);
    }
}
