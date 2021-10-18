package com.ryzhkov.cafe_vote.controller.cafe_controller;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.model.Cafe;
//import com.ryzhkov.cafe_vote.service.CafeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.ryzhkov.cafe_vote.util.ValidationUtil.assureIdConsistent;
import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = CafeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class CafeController {
    static final String REST_URL = "/api/cafes";

//    private final CafeService cafeService;
//
//    //for user
//    @GetMapping
//    public List<CafeDto> getAll() {
//        log.info("getAll");
//        return cafeService.getAll();
//    }
//
//    //for admin
//    @GetMapping("/my")
//    public List<CafeDto> getAllMine() {
//        log.info("getAllMine");
//        return cafeService.getByUserId(id);
//    }
//
//    @GetMapping("/{id}")
//    public CafeDto get(@PathVariable int id) {
//        log.info("get cafe {} for user {}", id, userId);
//        return cafeService.get(id, userId);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<CafeDto> create(@RequestBody Cafe cafe) {
//        int userId = authUserId();
//        log.info("create {} for user{}", cafe, userId);
//        checkNew(cafe);
//        CafeDto created = cafeService.save(cafe, userId);
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@RequestBody Cafe cafe, @PathVariable int id) {
//        assureIdConsistent(cafe, id);
//        log.info("update {} for user {}", cafe, userId);
//        cafeService.save(cafe, userId);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable int id) {
//        log.info("delete cafe {} for user {}", id, userId);
//        cafeService.delete(id, userId);
//    }
}
