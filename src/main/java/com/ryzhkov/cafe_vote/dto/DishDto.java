package com.ryzhkov.cafe_vote.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DishDto extends BaseDto {
    //    private LocalDate date;
    @NotBlank
    @Size(min = 2, max = 100)
    private String dish;
    @Min(0)
    private Integer price;
}
