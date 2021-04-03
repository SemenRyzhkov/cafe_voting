package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    Dish getByIdAndCafeId(int id, int cafeId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.cafe.id=:cafeId")
    void deleteByIdAndCafeId(int id, int cafeId);
}
