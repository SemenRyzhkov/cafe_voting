package com.ryzhkov.cafe_vote.repository;

import com.ryzhkov.cafe_vote.model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceRepository extends JpaRepository<Voice, Integer> {
}
