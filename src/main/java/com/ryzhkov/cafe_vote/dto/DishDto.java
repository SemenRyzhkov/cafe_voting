package com.ryzhkov.cafe_vote.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DishDto extends BaseDto {
//    private LocalDate date;
    private String dish;
    private Integer price;
}
