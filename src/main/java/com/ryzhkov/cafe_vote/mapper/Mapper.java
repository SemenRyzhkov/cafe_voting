package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.BaseDto;
import com.ryzhkov.cafe_vote.model.BaseEntity;

public interface Mapper<E extends BaseEntity, D extends BaseDto>{

    E toEntity(D dto);

    D toDto(E entity);
}
