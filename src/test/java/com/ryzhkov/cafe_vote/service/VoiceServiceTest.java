package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.repository.VoiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.ryzhkov.cafe_vote.test_data.CafeTestData.CAFE1_ID;
import static com.ryzhkov.cafe_vote.test_data.UserTestData.USER_ID;
import static com.ryzhkov.cafe_vote.test_data.VoiceTestData.*;

@Transactional
class VoiceServiceTest extends AbstractServiceTest {

    @Autowired
    private VoiceService service;

    @Autowired
    private VoiceRepository repository;

    @Test
    void makeVoice() {
        service.makeVoice(USER_ID, CAFE1_ID);
        VOICE_MATCHER.assertMatch(repository.getHistoryOfVoting(CAFE1_ID).size(), cafe1Voice.size());
    }

    @Test
    void makeDoubleVoice() {
        service.makeVoice(USER_ID, CAFE1_ID);
        VOICE_MATCHER.assertMatch(repository.getHistoryOfVoting(CAFE1_ID).size(), cafe1Voice.size());
        service.makeVoice(USER_ID, CAFE1_ID + 1);
        if (LocalTime.now().compareTo(LocalTime.of(11, 0)) < 0) {
            VOICE_MATCHER.assertMatch(repository.getHistoryOfVoting(CAFE1_ID + 1).size(), cafe2Voice.size() + 1);
        } else VOICE_MATCHER.assertMatch(repository.getHistoryOfVoting(CAFE1_ID + 1).size(), cafe2Voice.size());
    }

    @Test
    void historyOfVoting() {
        VOICE_MATCHER.assertMatch(repository.getHistoryOfVoting(CAFE1_ID + 1).size(), cafe2Voice.size());
    }

    @Test
    void todayVoting() {
        VOICE_MATCHER.assertMatch(repository.todayVoting(CAFE1_ID).size(),
                (int) cafe1Voice.
                        stream()
                        .filter(v -> v.getDate().compareTo(LocalDate.now()) == 0)
                        .count() - 1);
    }
}