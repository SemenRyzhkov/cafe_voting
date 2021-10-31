package com.ryzhkov.cafe_vote.controller;

import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Dish;
import com.ryzhkov.cafe_vote.service.DishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.ryzhkov.cafe_vote.util.ValidationUtil.assureIdConsistent;
import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DishController {

    private final DishService dishService;

    static final String REST_URL = "/api/cafes/";

    //for admin
    @GetMapping("{userId}/menu/{cafeId}")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    public Map<LocalDate, List<DishDto>> historyOfMenu(
            @PathVariable int cafeId,
            @PathVariable int userId,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("historyOfMenu for {} cafe", cafeId);
        return dishService.historyOfMenu(cafeId, userId);
        //http://localhost:8080/api/cafes/1/menu/1/
    }

    @GetMapping("{userId}/menu/{cafeId}/by-date")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    public List<DishDto> menuByDate(
            @PathVariable int userId,
            @PathVariable int cafeId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("menuByDate for {} cafe", cafeId);
        return dishService.menuByDate(cafeId, userId, date);
        //http://localhost:8080/api/cafes/1/menu/1/by-date?date=2021-04-02
    }

    //    for user: if admin has forgotten refresh menu, show yesterday menu
    @GetMapping("/menu/{cafeId}/by-date/cur-date")
    @PreAuthorize("hasAuthority('users:read')")
    public List<DishDto> todayMenuIfPresentOrElseYesterdayMenu(@PathVariable int cafeId) {
        log.info("todayMenuIfPresentOrElseYesterdayMenu for {} cafe", cafeId);
        return dishService.todayMenuIfPresentOrYesterdayMenu(cafeId);
        //http://localhost:8080/api/cafes/menu/1/by-date/cur-date
    }

    @PostMapping(value = "{userId}/menu/{cafeId}/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    public ResponseEntity<DishDto> create(
            @PathVariable int userId,
            @PathVariable int cafeId,
            @RequestBody Dish dish,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("create dish {} for cafe {}", dish, cafeId);
        checkNew(dish);
        DishDto created = dishService.save(dish, userId, cafeId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
        //http://localhost:8080/api/cafes/1/menu/1/dish/
    }

    @PutMapping("{userId}/menu/{cafeId}/dish/{id}")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish,
                       @PathVariable int userId,
                       @PathVariable int cafeId,
                       @PathVariable int id,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        assureIdConsistent(dish, id);
        log.info("update dish {} for cafe{}", dish, cafeId);
        dishService.save(dish, userId, cafeId);
//        http://localhost:8080/api/cafes/1/menu/1/dish/13
//          {
//            "dish": "zzzzzzzzz",
//            "price": 1308
//        }
    }

    @DeleteMapping("{userId}/menu/{cafeId}/dish/{id}")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int userId,
                       @PathVariable int id,
                       @PathVariable int cafeId,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("delete dish {} for cafe {}", id, cafeId);
        dishService.delete(id, userId, cafeId);
//        http://localhost:8080/api/cafes/1/menu/1/dish/13
    }
}
