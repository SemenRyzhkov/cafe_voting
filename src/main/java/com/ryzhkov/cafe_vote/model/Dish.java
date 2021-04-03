package com.ryzhkov.cafe_vote.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"dish", "date", "cafe_id"}, name = "dish_unique_index")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Dish extends BaseEntity{
    @Column(name = "date", nullable = false)
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "dish", nullable = false)
    private String dish;

    @Min(0)
    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    private Cafe cafe;
}
