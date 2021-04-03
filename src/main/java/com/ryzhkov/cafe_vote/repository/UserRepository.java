package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM User u WHERE u.id=:id")
//    void delete(@Param("id") int id);

    @Transactional
    @Modifying
    void deleteById(Integer id);

    User getByEmail(String email);
}
