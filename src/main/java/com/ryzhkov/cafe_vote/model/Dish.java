package com.ryzhkov.cafe_vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dishes", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"dish", "date", "cafe_id"}, name = "dish_unique_index")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @JsonBackReference
    private Cafe cafe;

    public Dish(Dish dish){
        this(dish.getId(), dish.getDish(), dish.getDate(),  dish.getPrice());
    }

    public Dish(Integer id,String dish, LocalDate date,   Integer price) {
        super(id);
        this.date = date;
        this.dish = dish;
        this.price = price;
    }
}
