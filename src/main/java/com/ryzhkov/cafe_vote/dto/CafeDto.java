package com.ryzhkov.cafe_vote.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CafeDto extends BaseDto {
    @NotBlank
    @Size(min = 2, max = 100)
    private String cafeName;
    private String description;
    @NotBlank
    @URL
    private String homepage;
}


