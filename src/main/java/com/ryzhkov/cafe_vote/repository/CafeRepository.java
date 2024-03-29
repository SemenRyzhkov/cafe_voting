package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CafeRepository extends JpaRepository<Cafe, Integer> {

    //написать запрос для кафе с джойном отфильтрованной по дате таблицей меню

    @Query("SELECT DISTINCT c, d " +
            "FROM Cafe c " +
            "LEFT JOIN FETCH c.menu d " +
            "WHERE c.id=:cafeId " +
            "AND d.date=current_date ")
    Optional<Cafe> getWithTodayMenu(@Param("cafeId") int cafeId);

    @Query("SELECT DISTINCT c, d " +
            "FROM Cafe c " +
            "LEFT JOIN FETCH c.menu d " +
            "WHERE c.id=:cafeId " +
            "AND d.date=current_date-1 ")
    Optional<Cafe> getWithYesterdayMenu(@Param("cafeId") int cafeId);

    @Query("SELECT  DISTINCT  c, d " +
            "FROM Cafe c " +
            "LEFT JOIN FETCH c.menu d " +
            "WHERE c.id=:cafeId " +
            "AND d.date=:date")
    Optional<Cafe>getWithMenuByDate(@Param("cafeId") int cafeId, @Param("date") LocalDate date);

        @Query("SELECT c FROM Cafe c WHERE c.user.id=:userId")
    List<Cafe> getAllByUserId(int userId);

    @Query("SELECT c FROM Cafe c LEFT JOIN FETCH c.user WHERE c.user.id=:userId")
    List<Cafe> getByUserId(@Param("userId") int userId);

    @Query("SELECT c FROM Cafe c LEFT JOIN FETCH c.user ")
    List<Cafe> getAll();

    @Query("SELECT c FROM Cafe c LEFT JOIN FETCH c.menu WHERE c.id=:id")
    Optional<Cafe> findByIdWithMenu(@Param("id") int id);

//    https://stackoverflow.com/questions/7825484/jpa-delete-where-does-not-delete-children-and-throws-an-exception
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Cafe c WHERE c.id=:id AND c.user.id=:userId")
//    int delete(@Param("id") int id, @Param("userId") int userId);

    Cafe findByIdAndUserId(int id, int userId);

    @Transactional
    @Modifying
    void deleteByIdAndUserId(int id, int userId);

    @Transactional
    @Modifying
    void deleteByUserId(int userId);

}
