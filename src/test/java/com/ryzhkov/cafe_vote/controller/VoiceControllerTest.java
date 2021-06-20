package com.ryzhkov.cafe_vote.controller;

import com.ryzhkov.cafe_vote.controller.json.JsonUtil;
import com.ryzhkov.cafe_vote.repository.VoiceRepository;
import com.ryzhkov.cafe_vote.test_data.CafeTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.ryzhkov.cafe_vote.test_data.VoiceTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoiceControllerTest extends AbstractControllerTest{
    private static final String REST_URL = VoiceController.REST_URL;

    @Autowired
    private VoiceRepository repository;

    @Test
    void makeVoice() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "makeVoice?cafeId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        VOICE_MATCHER.assertMatch(repository.getHistoryOfVoting(CafeTestData.CAFE1_ID).size(), cafe1Voice.size());
    }
    

    @Test
    void historyOfVoting() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "history?cafeId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void todayVoting() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "today?cafeId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}