package com.ryzhkov.cafe_vote.mapper;

import com.ryzhkov.cafe_vote.dto.userDto.AdminDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserEditDto;
import com.ryzhkov.cafe_vote.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @InheritInverseConfiguration
    User toEntity(UserDto dto);

    User dtoToEntity(UserEditDto editDto);

    UserEditDto entityToEditDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromEditDto(UserEditDto userEditDto, @MappingTarget User user);

    @BeanMapping(
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromAdminDto(AdminDto adminDto, @MappingTarget User user);

    List<UserDto> usersToUsersDto(List<User> users);

    @InheritInverseConfiguration
    List<User> usersDtoToUsers(List<UserDto> dtos);

}