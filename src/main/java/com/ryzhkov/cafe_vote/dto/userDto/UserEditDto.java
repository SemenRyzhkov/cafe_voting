package com.ryzhkov.cafe_vote.dto.userDto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserEditDto {
    @Size(min=3,max=30)
    private String name;
    @Email
    private String email;
    @Size(min=7,max=15)
    private String password;
}
