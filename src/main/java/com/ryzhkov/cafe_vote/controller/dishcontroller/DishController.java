package com.ryzhkov.cafe_vote.controller.dishcontroller;

import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Dish;
import com.ryzhkov.cafe_vote.service.DishService;
import com.ryzhkov.cafe_vote.util.SecurityUtil;
import com.ryzhkov.cafe_vote.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.ryzhkov.cafe_vote.util.ValidationUtil.*;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class DishController {

    private final DishService dishService;

    static final String REST_URL = "/api/cafes/";

    //for admin
    @GetMapping("{cafeId}/menu")
    public Map<LocalDate, List<DishDto>> historyOfMenu(@PathVariable int cafeId) {
        int userId = SecurityUtil.authUserId();
        log.info("historyOfMenu for {} cafe", cafeId);
        return dishService.historyOfMenu(cafeId, userId);
    }

    @GetMapping("{cafeId}/menu/by-date")
    public List<DishDto> menuByDate(
            @PathVariable int cafeId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date) {
        int userId = SecurityUtil.authUserId();
        log.info("menuByDate for {} cafe", cafeId);
        return dishService.menuByDate(cafeId, userId, date);
    }

    //    for user: if admin has forgotten refresh menu, show yesterday menu
    @GetMapping("{cafeId}/menu/by-date/cur-date")
    public List<DishDto> todayMenuIfPresentOrElseYesterdayMenu(@PathVariable int cafeId) {
        log.info("todayMenuIfPresentOrElseYesterdayMenu for {} cafe", cafeId);
        return dishService.todayMenuIfPresentOrYesterdayMenu(cafeId);
    }

    @PostMapping(value = "{cafeId}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishDto> create(@PathVariable int cafeId, @RequestBody Dish dish) {
        int userId = SecurityUtil.authUserId();
        log.info("create dish {} for cafe {}", dish, cafeId);
        checkNew(dish);
        DishDto created = dishService.save(dish, userId, cafeId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("{cafeId}/menu/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int cafeId, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(dish, id);
        log.info("update dish {} for cafe{}", dish, cafeId);
        dishService.save(dish, userId, cafeId);
    }

    @DeleteMapping("{cafeId}/menu/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int cafeId) {
        int userId = SecurityUtil.authUserId();
        log.info("delete dish {} for cafe {}", id, cafeId);
        dishService.delete(id, userId, cafeId);
    }
}
