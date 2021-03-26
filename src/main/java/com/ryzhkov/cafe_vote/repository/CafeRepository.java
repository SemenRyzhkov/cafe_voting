package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CafeRepository extends JpaRepository<Cafe, Integer> {

    @Query("SELECT DISTINCT c, d FROM Cafe c LEFT JOIN FETCH c.menu d WHERE d.date=current_date")
    List<Cafe> getWithTodayMenu();

    @Query("SELECT DISTINCT c, d FROM Cafe c LEFT JOIN FETCH c.menu d WHERE d.date=:date")
    List<Cafe> getWithMenuByDate(@Param("date") LocalDate date);

    @Query("SELECT DISTINCT c, d " +
            "FROM Cafe c " +
            "LEFT JOIN FETCH c.menu d " +
            "WHERE c.user.id=:userId AND d.date=current_date ")
    List<Cafe> getByUserIdWithTodayMenu(@Param("userId") int userId);
}
