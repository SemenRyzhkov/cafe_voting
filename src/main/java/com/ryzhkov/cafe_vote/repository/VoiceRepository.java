package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoiceRepository extends JpaRepository<Voice, Integer> {

    @Transactional
    @Query("SELECT v FROM Voice v WHERE v.userId=:userId AND v.date=current_date ")
    Optional<Voice> findByUserIdAndCurrentDate(@Param("userId") int userid);

    @Query("SELECT v FROM Voice v WHERE v.cafe.id=:cafeId")
    List<Voice> getHistoryOfVoting(@Param("cafeId") int cafeId);

}
