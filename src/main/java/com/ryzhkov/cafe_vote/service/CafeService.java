package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.repository.CafeRepository;
import com.ryzhkov.cafe_vote.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;
    private final DishRepository dishRepository;

    public List<Cafe> getMenuByDate(LocalDate date){
       return cafeRepository.getWithMenuByDate(date);
    }

    public List<Cafe> getWithTodayMenu(){
       return cafeRepository.getWithTodayMenu();
    }

    public List<Cafe> getByUserId(int userId){
        return cafeRepository.getByUserIdWithTodayMenu(userId);
    }

}
