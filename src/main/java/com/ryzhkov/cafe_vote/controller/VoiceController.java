package com.ryzhkov.cafe_vote.controller;

import com.ryzhkov.cafe_vote.service.VoiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

import static com.ryzhkov.cafe_vote.util.SecurityUtil.authUserId;

@RestController
@RequestMapping("/api/cafe/")
@AllArgsConstructor
@Slf4j
public class VoiceController {
    private final VoiceService voiceService;

    @GetMapping("/{cafeId}/makeVoice")
    public String makeVoice(@PathVariable("cafeId") int cafeId){
        int userId = authUserId();
        return voiceService.makeVoice(userId, cafeId);
    }

    @GetMapping("/{cafeId}/history")
    public Map<LocalDate, Long> historyOfVoting(@PathVariable("cafeId") int cafeId){
        int userId = authUserId();
        return voiceService.historyOfVoting(userId, cafeId);
    }
}
