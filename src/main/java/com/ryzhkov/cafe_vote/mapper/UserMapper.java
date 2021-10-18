package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.UserDto;
import com.ryzhkov.cafe_vote.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @InheritInverseConfiguration
    User toEntity(UserDto dto);

}