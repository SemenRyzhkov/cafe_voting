package com.ryzhkov.cafe_vote.controller.dishcontroller;

import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Dish;
import com.ryzhkov.cafe_vote.service.DishService;
import com.ryzhkov.cafe_vote.util.SecurityUtil;
import com.ryzhkov.cafe_vote.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cafe/")
@AllArgsConstructor
@Slf4j
public class DishController {

    private final DishService dishService;

    //for admin
    @GetMapping("{cafeId}/menu")
    public Map<LocalDate, List<DishDto>> historyOfMenu(@PathVariable int cafeId) {
        int userId = SecurityUtil.authUserId();
        log.info("historyOfMenu for {}", cafeId);
        return dishService.historyOfMenu(cafeId, userId);
    }

    @GetMapping("{cafeId}/menu/by-date")
    public List<DishDto> menuByDate(
            @PathVariable int cafeId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate date) {
        int userId = SecurityUtil.authUserId();
        log.info("menuByDate for {}", cafeId);
        return dishService.menuByDate(cafeId, userId, date);
    }

    //    for user: if admin forgot refresh menu, find yesterday menu
    @GetMapping("{cafeId}/menu/by-date/curdate")
    public List<DishDto> todayMenuIfPresentOrElseYesterdayMenu(@PathVariable int cafeId) {
        log.info("todayMenuIfPresentOrElseYesterdayMenu for {}", cafeId);
        return dishService.todayMenuIfPresentOrYesterdayMenu(cafeId);
    }

    @PostMapping("{cafeId}/menu")
    public DishDto create(@PathVariable int cafeId, @RequestBody Dish dish) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkNew(dish);
        log.info("create dish {} for cafe {}", dish, cafeId);
        return dishService.save(dish, userId, cafeId);
    }

    @PutMapping("{cafeId}/menu/{id}")
    public DishDto update(@RequestBody Dish dish, @PathVariable int cafeId, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.assureIdConsistent(dish, id);
        log.info("update dish {} for cafe{}", dish, cafeId);
        return dishService.save(dish, userId, cafeId);
    }

    @DeleteMapping("{cafeId}/menu/{id}")
    public void delete(@PathVariable int id, @PathVariable int cafeId) {
        int userId = SecurityUtil.authUserId();
        log.info("delete dish {} for cafe {}", id, cafeId);
        dishService.delete(id, userId, cafeId);
    }
}
