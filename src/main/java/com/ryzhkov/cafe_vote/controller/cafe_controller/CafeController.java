package com.ryzhkov.cafe_vote.controller.cafe_controller;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.service.CafeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.ryzhkov.cafe_vote.util.DateTimeUtil.convert;
import static com.ryzhkov.cafe_vote.util.SecurityUtil.authUserId;
import static com.ryzhkov.cafe_vote.util.ValidationUtil.assureIdConsistent;
import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class CafeController {

    private final CafeService cafeService;

    //for user
    @GetMapping("/cafes")
    public List<CafeDto> getAllWithTodayMenuAndVoices() {
        log.info("getAllWithTodayMenuAndVoices");
        return cafeService.getAllWithTodayMenuAndVoices();
    }

    //for admin
    @GetMapping("/cafes/my")
    public List<CafeDto> getAllMine() {
        int id = authUserId();
        log.info("getAllMine");
        return cafeService.getByUserId(id);
    }

    @GetMapping("/cafes/{id}")
    public CafeDto get(@PathVariable int id) {
        int userId = authUserId();
        log.info("get cafe {} for user {}", id, userId);
        return cafeService.get(id, userId);
    }

    @GetMapping("/dish/{cafeId}")
    public Set<DishDto> getMenu(@PathVariable int cafeId) {
        return get(cafeId).getMenu();
    }


    @PostMapping("/cafes")
    public Cafe create(@RequestBody Cafe cafe) {
        int userId = authUserId();
        checkNew(cafe);
        log.info("create {} for user{}", cafe, userId);
        return cafeService.save(cafe, userId);
    }

    @PutMapping("/cafes/{id}")
    public Cafe update(@RequestBody Cafe cafe, @PathVariable int id) {
        int userId = authUserId();
        assureIdConsistent(cafe, id);
        log.info("update {} for user {}", cafe, userId);
        return cafeService.save(cafe, userId);
    }

    @DeleteMapping("/cafes/{id}")
    public void delete(@PathVariable int id) {
        int userId = authUserId();
        log.info("delete cafe {} for user {}", id, userId);
        cafeService.delete(id, userId);
    }
}
