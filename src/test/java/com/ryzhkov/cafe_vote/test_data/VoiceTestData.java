package com.ryzhkov.cafe_vote.test_data;

import com.ryzhkov.cafe_vote.TestMatcher;
import com.ryzhkov.cafe_vote.dto.VoiceDto;
import com.ryzhkov.cafe_vote.model.Voice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.ryzhkov.cafe_vote.test_data.CafeTestData.*;

public class VoiceTestData {
    public static final TestMatcher VOICE_MATCHER = TestMatcher.usingEqualsComparator(VoiceDto.class);

    public static final int VOICE_ID = 1;


    public static final Voice voice1 = new Voice(VOICE_ID, LocalDate.now(), LocalTime.of(22, 34), 4);
    public static final Voice voice2 = new Voice(VOICE_ID + 1, LocalDate.now(), LocalTime.of(22, 35), 2);
    public static final Voice voice3 = new Voice(VOICE_ID + 2, LocalDate.now(), LocalTime.of(22, 35), 3);
    public static final Voice voice4 = new Voice(VOICE_ID + 3, LocalDate.of(2021, 03, 30), LocalTime.of(22, 35), 1);
    public static final Voice voice5 = new Voice(VOICE_ID + 4, LocalDate.of(2021, 03, 30), LocalTime.of(22, 35), 2);
    public static final Voice voice6 = new Voice(VOICE_ID + 5, LocalDate.of(2021, 03, 30), LocalTime.of(22, 35), 3);
    public static final Voice voice7 = new Voice(VOICE_ID + 6, LocalDate.of(2021, 03, 30), LocalTime.of(22, 35), 1);
    public static final Voice voice8 = new Voice(VOICE_ID + 7, LocalDate.of(2021, 03, 30), LocalTime.of(22, 35), 2);
    public static final Voice voice9 = new Voice(VOICE_ID + 8, LocalDate.of(2021, 03, 30), LocalTime.of(22, 31), 3);
    public static final Voice voice10 = new Voice(VOICE_ID, LocalDate.now(), LocalTime.now(), 1);

    public static final List<Voice> cafe1Voice = List.of(voice1, voice2, voice3, voice4, voice5, voice6, voice10);
    public  static final List<Voice> cafe2Voice = List.of(voice7, voice8, voice9);
//
//    static {
//        cafe1Voice.forEach(voice -> voice.setCafeId(CAFE1_ID));
//        cafe2Voice.forEach(voice -> voice.setCafeId(CAFE1_ID+1));
//    }
}
