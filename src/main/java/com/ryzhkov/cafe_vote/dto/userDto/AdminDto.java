package com.ryzhkov.cafe_vote.dto.userDto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AdminDto {
    @Pattern(regexp = "USER|ADMIN")
    private String role;
    @Pattern(regexp = "ACTIVE|BANNED")
    private String status;
}
