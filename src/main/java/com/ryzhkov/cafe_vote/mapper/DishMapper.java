package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Dish;
import com.ryzhkov.cafe_vote.repository.CafeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DishMapper extends AbstractMapper<Dish, DishDto> {
    @Autowired
    public DishMapper() {
        super(Dish.class, DishDto.class);
    }
}
