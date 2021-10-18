package com.ryzhkov.cafe_vote.dto;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Data
public class CafeDto extends BaseDto {
    private String cafeName;
    private String description;
    private String homepage;
    private Integer userId;
}


