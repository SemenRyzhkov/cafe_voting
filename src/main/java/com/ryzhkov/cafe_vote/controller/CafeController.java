package com.ryzhkov.cafe_vote.controller;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.service.CafeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = CafeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class CafeController {
    static final String REST_URL = "/api/cafes";

    private final CafeService cafeService;

    //for user
    @GetMapping
    public List<CafeDto> getAll() {
        log.info("getAll");
        return cafeService.getAll();
    }

    //for admin
    @GetMapping("/{userId}")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    public List<CafeDto> getAllMine(@PathVariable int userId,
                                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("getAllMine");
        return cafeService.getByUserId(userId);
    }

    @GetMapping("/{userId}/{id}")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public CafeDto get(@PathVariable int id,
                       @PathVariable int userId,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("get cafe {} for user {}", id, userId);
        return cafeService.get(id, userId);
    }

    @PostMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<CafeDto> create(@RequestBody CafeDto cafe,
                                          @PathVariable int userId) {
        log.info("create {} for user{}", cafe, userId);
        CafeDto created = cafeService.save(cafe, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{userId}/{id}")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    public CafeDto update(@RequestBody @Valid CafeDto cafeDto,
                          @PathVariable int userId,
                          @PathVariable int id,
                          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("update {} for user {}", cafeDto, userId);
        return cafeService.update(cafeDto, id, userId);
    }

    @DeleteMapping("/{userId}/{id}")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public void delete(@PathVariable int userId,
                       @PathVariable int id,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("delete cafe {} for user {}", id, userId);
        cafeService.delete(id, userId);
    }
}
