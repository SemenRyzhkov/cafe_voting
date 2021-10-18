package com.ryzhkov.cafe_vote.controller;

//import com.ryzhkov.cafe_vote.service.VoiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;


@RestController
@RequestMapping(value = VoiceController.REST_URL,  produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoiceController {
    static final String REST_URL = "/api/cafes/";
//
//    private final VoiceService voiceService;
//
//    @GetMapping("makeVoice")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public String makeVoice(@RequestParam int cafeId){
//        log.info("makeVoice for {} cafe", cafeId);
//        int userId = authUserId();
//        return voiceService.makeVoice(userId, cafeId);
//    }
//
//    @GetMapping("history")
//    public Map<LocalDate, Long> historyOfVoting(@RequestParam int cafeId){
//        int userId = authUserId();
//        log.info("historyOfVoting for {} cafe", cafeId);
//        return voiceService.historyOfVoting(userId, cafeId);
//    }
//
//    @GetMapping("today")
//    public int todayVoting(@RequestParam int cafeId){
//        int userId = authUserId();
//        log.info("today voting for {} cafe", cafeId);
//        return voiceService.todayVoting(userId, cafeId);
//    }
}
