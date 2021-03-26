package com.ryzhkov.cafe_vote.controller.cafe_controller;

import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.service.CafeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ryzhkov.cafe_vote.util.DateTimeUtil.convert;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/cafes")
    public List<Cafe> getWithTodayMenu(){
        return cafeService.getWithTodayMenu();
    }

    @GetMapping("/cafes/by-date/{date}")
    public List<Cafe> getWithMenuByDate(@PathVariable String date){
        return cafeService.getMenuByDate(convert(date));
    }

    @GetMapping("/cafes/by-userId/{userId}")
    public List<Cafe> getByUserIdWithTodayMenu(@PathVariable int userId){
        return cafeService.getByUserId(userId);
    }
}
