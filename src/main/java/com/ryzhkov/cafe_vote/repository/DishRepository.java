package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Cafe> findByDateEquals(LocalDate date);
}
