package com.ryzhkov.cafe_vote.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "voices", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id", "cafe_id"}, name = "voice_unique_index")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Voice extends BaseEntity {
    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private int userId;
}
