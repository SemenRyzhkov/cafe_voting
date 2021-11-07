package com.ryzhkov.cafe_vote.dto.userDto;

import com.ryzhkov.cafe_vote.model.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto {

    private Integer id;

    private String name;

    private String email;

    private String role;

    private Status status;
}
