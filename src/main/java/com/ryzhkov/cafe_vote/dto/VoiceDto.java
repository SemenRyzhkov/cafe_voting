package com.ryzhkov.cafe_vote.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoiceDto extends BaseDto {

    private LocalDate date;
    private LocalTime time;

}
