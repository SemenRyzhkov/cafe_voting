package com.ryzhkov.cafe_vote.dto;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CafeDto extends BaseDto {
    private String cafeName;
    private String description;
    private String homepage;
    private Set<DishDto> menu;
    private Integer voicesToday;
    private Integer userId;
}
