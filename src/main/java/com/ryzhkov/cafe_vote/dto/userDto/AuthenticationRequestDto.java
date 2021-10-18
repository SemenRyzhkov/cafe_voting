package com.ryzhkov.cafe_vote.dto.userDto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String email;

    private String password;
}
