package com.ryzhkov.cafe_vote.controller;

import com.ryzhkov.cafe_vote.service.VoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;


@RestController
@RequestMapping(value = VoiceController.REST_URL,  produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoiceController {
    public static final String REST_URL = "/api/cafes/voting/";

    private final VoiceService voiceService;

    @Autowired
    public VoiceController(VoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @GetMapping("{userId}/makeVoice")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:read')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> makeVoice(@RequestParam int cafeId,
                                    @PathVariable int userId,
                                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
        log.info("makeVoice for {} cafe", cafeId);
        return new ResponseEntity<>(voiceService.makeVoice(userId, cafeId), HttpStatus.OK) ;
//        http://localhost:8080/api/cafes/voting/3/makeVoice?cafeId=3
    }

    @GetMapping("{userId}/history")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    public Map<LocalDate, Long> historyOfVoting(
            @PathVariable int userId,
            @RequestParam int cafeId,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
        log.info("historyOfVoting for {} cafe", cafeId);
        return voiceService.historyOfVoting(userId, cafeId);
    }

    @GetMapping("{userId}/today")
    @PreAuthorize("#userId.equals(#usernamePasswordAuthenticationToken.principal.id) && hasAuthority('users:write')")
    public int todayVoting(
            @PathVariable int userId,
            @RequestParam int cafeId,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
        log.info("today voting for {} cafe", cafeId);
        return voiceService.todayVoting(userId, cafeId);
//        http://localhost:8080/api/cafes/voting/1/today?cafeId=1
    }
}
