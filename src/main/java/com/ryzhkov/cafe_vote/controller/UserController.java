package com.ryzhkov.cafe_vote.controller;


import com.ryzhkov.cafe_vote.dto.userDto.AdminDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserEditDto;
import com.ryzhkov.cafe_vote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = UserController.REST_URL)
@Slf4j
public class UserController {
    public static final String REST_URL = "/api/users";

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:write')")
    public List<UserDto> getAll(
            @PageableDefault Pageable pageable) {
        log.info("getAllUser");
        return service.getAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public UserDto get(@PathVariable int id,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("get user {}", id);
        return service.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto create(@RequestBody @Valid UserEditDto userDto) {
        log.info("create user {}", userDto);
        return service.create(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/admin-edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('users:write')")
    public UserDto updateForAdmin(@RequestBody @Valid AdminDto adminDto, @PathVariable int id) {
        log.info("update user {}", adminDto);
        return service.updateForAdmin(adminDto, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user-edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public UserDto update(@RequestBody @Valid UserEditDto userDto, @PathVariable int id,
                          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("update user {}", userDto);
        return service.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("delete user{}", id);
        service.delete(id);
    }
}

