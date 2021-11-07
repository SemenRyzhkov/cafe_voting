package com.ryzhkov.cafe_vote.controller.dishcontroller;

import com.ryzhkov.cafe_vote.controller.AbstractControllerTest;
import com.ryzhkov.cafe_vote.controller.DishController;
import com.ryzhkov.cafe_vote.controller.json.JsonUtil;
import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.mapper.DishMapper;
import com.ryzhkov.cafe_vote.model.Dish;
import com.ryzhkov.cafe_vote.service.DishService;
import com.ryzhkov.cafe_vote.test_data.DishTestData;
import com.ryzhkov.cafe_vote.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.ryzhkov.cafe_vote.TestUtil.readFromJson;
import static com.ryzhkov.cafe_vote.test_data.CafeTestData.CAFE1_ID;
import static com.ryzhkov.cafe_vote.test_data.DishTestData.*;
import static com.ryzhkov.cafe_vote.test_data.UserTestData.ADMIN_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishController.REST_URL;

    @Autowired
    private DishMapper mapper;

    @Autowired
    private DishService service;

    @Test
    void historyOfMenu() throws Exception {
        TreeMap<LocalDate, List<DishDto>> history = new TreeMap<>(menu1
                .stream()
                .collect(Collectors.groupingBy(Dish::getDate, Collectors.mapping(mapper::toDto, Collectors.toList()))));
        history.values().forEach(list -> list.sort(Comparator.comparing(DishDto::getDish)));
        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID + "/menu/" + CAFE1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_DTO_MATCHER.contentJson(history));
    }

    @Test
    void menuByDate() throws Exception {
        List<DishDto> list = mapper.toDtoList(menu_2021_04_03);
        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID + "/menu/" + CAFE1_ID + "/by-date?date=2021-04-03"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_DTO_MATCHER.contentJson(list));
    }

    @Test
    void todayMenuIfPresentOrElseYesterdayMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/menu/" + (CAFE1_ID + 1) + "/by-date/cur-date"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_DTO_MATCHER.contentJson(mapper.toDtoList(menu2)));
    }

    @Test
    void create() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(
                REST_URL + ADMIN_ID + "/menu/" + CAFE1_ID + "/dish")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.toDto(newDish))))
                .andExpect(status().isCreated());

        DishDto created = readFromJson(action, DishDto.class);
        int newId = created.getId();
        newDish.setId(newId);
        DishDto newCafeDto = mapper.toDto(newDish);
        DISH_DTO_MATCHER.assertMatch(created, newCafeDto);
        DISH_DTO_MATCHER.assertMatch(mapper.toDto(service.get(newId, CAFE1_ID)), newCafeDto);
    }

    @Test
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + ADMIN_ID + "/menu/" + CAFE1_ID  + "/dish/" + DISH_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        DishDto updDto = mapper.toDto(updated);
        DISH_DTO_MATCHER.assertMatch(mapper.toDto(service.get(CAFE1_ID, CAFE1_ID)), updDto);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + ADMIN_ID + "/menu/" + CAFE1_ID  + "/dish/" + DISH_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(DISH_1, CAFE1_ID));
    }
}