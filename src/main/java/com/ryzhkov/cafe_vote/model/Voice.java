package com.ryzhkov.cafe_vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "voices", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id", "cafe_id"}, name = "voice_unique_index")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Voice extends BaseEntity {
    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @NotNull
    private LocalTime time;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    //
//    @ManyToOne
//    @JsonBackReference
//    private Cafe cafe;
    @NotNull
    @Column(name = "cafe_id", nullable = false)
    private Integer cafeId;

    public Voice(Integer id, LocalDate date, LocalTime time, Integer userId) {
        super(id);
        this.date = date;
        this.time = time;
        this.userId = userId;
    }
}
