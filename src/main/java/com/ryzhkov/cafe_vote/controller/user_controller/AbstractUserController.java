package com.ryzhkov.cafe_vote.controller.user_controller;

import com.ryzhkov.cafe_vote.dto.UserDto;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.ryzhkov.cafe_vote.util.ValidationUtil.assureIdConsistent;
import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNew;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractUserController {

    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public UserDto get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public User update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        return service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
