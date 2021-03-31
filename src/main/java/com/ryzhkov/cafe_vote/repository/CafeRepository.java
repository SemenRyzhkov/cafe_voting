package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = false)
public interface CafeRepository extends JpaRepository<Cafe, Integer> {

    //написать запрос для кафе с джойном отфильтрованной по дате таблицей меню

    @Query("SELECT DISTINCT c " +
            "FROM Cafe c " +
            "LEFT JOIN FETCH c.menu d " +
            "LEFT JOIN FETCH c.voices v " +
            "WHERE d.date=current_date " +
            "AND v.date=current_date")
    List<Cafe> getWithTodayMenuAndVoices();

    //    @Query("SELECT c FROM Cafe c WHERE c.user.id=:userId")
    List<Cafe> getAllByUserId(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cafe c WHERE c.id=:id AND c.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
}
