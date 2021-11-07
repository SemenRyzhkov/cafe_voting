package com.ryzhkov.cafe_vote.controller.user_controller;

import com.ryzhkov.cafe_vote.controller.AbstractControllerTest;
import com.ryzhkov.cafe_vote.controller.UserController;
import com.ryzhkov.cafe_vote.dto.userDto.AdminDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserEditDto;
import com.ryzhkov.cafe_vote.mapper.UserMapper;
import com.ryzhkov.cafe_vote.test_data.UserTestData;
import com.ryzhkov.cafe_vote.controller.json.JsonUtil;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.service.UserService;
import com.ryzhkov.cafe_vote.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.ryzhkov.cafe_vote.TestUtil.readFromJson;
import static com.ryzhkov.cafe_vote.test_data.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserController.REST_URL ;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + '/' + ADMIN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_DTO_MATCHER.contentJson(userMapper.toDto(admin1)));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + '/' + USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(EntityNotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    void update() throws Exception {
        User updated = UserTestData.getUpdated();
        UserEditDto dtoUpdated = userMapper.entityToEditDto(updated);
        perform(MockMvcRequestBuilders.put(REST_URL + "/user-edit/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(dtoUpdated)))
                .andExpect(status().isOk());

        USER_DTO_MATCHER.assertMatch(userService.get(USER_ID), userMapper.toDto(updated));
    }

    @Test
    void create() throws Exception {
        User newUser = getNew();
        UserEditDto newDtoUser = userMapper.entityToEditDto(newUser);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDtoUser)))
                .andExpect(status().isCreated());

        UserDto created = readFromJson(action, UserDto.class);
        int newId = created.getId();
        newUser.setId(newId);
        USER_DTO_MATCHER.assertMatch(created, userMapper.toDto(newUser));
        USER_DTO_MATCHER.assertMatch(userService.get(newId), userMapper.toDto(newUser));
    }

    @Test
    void updateForAdmin() throws Exception {
        AdminDto updated = new AdminDto();
        updated.setRole("ADMIN");
        updated.setStatus("BANNED");
        perform(MockMvcRequestBuilders.put(REST_URL + "/admin-edit/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        USER_DTO_MATCHER.assertMatch(userService.get(USER_ID), userMapper.toDto(getUpdatedAdmin()));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_DTO_MATCHER.contentJson(userMapper.usersToUsersDto(List.of(admin1, admin2, user, user2))));
    }
}