package com.ryzhkov.cafe_vote.dto;

import lombok.*;

import java.util.Objects;
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
//    private Set<DishDto> menu;
//    private Integer voicesToday;
    private Integer userId;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        CafeDto cafeDto = (CafeDto) o;
//        return Objects.equals(cafeName, cafeDto.cafeName) &&
//                Objects.equals(description, cafeDto.description) &&
//                Objects.equals(homepage, cafeDto.homepage) &&
//                Objects.equals(userId, cafeDto.userId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), cafeName, description, homepage, userId);
//    }
}


