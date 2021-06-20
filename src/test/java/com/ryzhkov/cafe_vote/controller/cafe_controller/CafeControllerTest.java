package com.ryzhkov.cafe_vote.controller.cafe_controller;

import com.ryzhkov.cafe_vote.test_data.CafeTestData;
import com.ryzhkov.cafe_vote.controller.AbstractControllerTest;
import com.ryzhkov.cafe_vote.controller.json.JsonUtil;
import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.mapper.CafeMapper;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.service.CafeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.ryzhkov.cafe_vote.test_data.CafeTestData.*;
import static com.ryzhkov.cafe_vote.TestUtil.readFromJson;
import static com.ryzhkov.cafe_vote.test_data.UserTestData.admin1;
import static com.ryzhkov.cafe_vote.util.SecurityUtil.authUserId;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CafeControllerTest extends AbstractControllerTest {

    private static final String REST_URL = CafeController.REST_URL + "/";

    @Autowired
    private CafeService service;
    @Autowired
    private CafeMapper mapper;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CAFE_DTO_MATCHER.contentJson(mapper.toDto(allCafe)));
    }

    @Test
    void getAllMine() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "my/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CAFE_DTO_MATCHER.contentJson(mapper.toDto(admin1Cafe)));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + CAFE1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CAFE_DTO_MATCHER.contentJson(mapper.toDto(cafe1)));
    }

    @Test
    void create() throws Exception {
        Cafe newCafe = CafeTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newCafe)))
                .andExpect(status().isCreated());

        CafeDto created = readFromJson(action, CafeDto.class);
        int newId = created.getId();
        newCafe.setUser(admin1);
        newCafe.setId(newId);
        CafeDto newCafeDto = mapper.toDto(newCafe);
        CAFE_DTO_MATCHER.assertMatch(created, newCafeDto);
        CAFE_DTO_MATCHER.assertMatch(service.get(newId, authUserId()), newCafeDto);
    }

    @Test
    void update() throws Exception {
        Cafe updated = CafeTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + CAFE1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        updated.setUser(admin1);
        CafeDto updDto = mapper.toDto(updated);

        CAFE_DTO_MATCHER.assertMatch(service.get(CAFE1_ID, authUserId()), updDto);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + CAFE1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        CAFE_DTO_MATCHER.assertMatch(service.get(CAFE1_ID, authUserId()), null);
    }
}