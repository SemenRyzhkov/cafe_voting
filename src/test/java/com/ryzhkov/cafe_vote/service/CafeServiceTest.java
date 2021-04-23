package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.mapper.CafeMapper;
import com.ryzhkov.cafe_vote.model.Cafe;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CafeServiceTest extends AbstractServiceTest {
    @Autowired
    private CafeService service;

    @Autowired
    private CafeMapper cafeMapper;

    @Test
    void getAll() {
    }

    @Test
    void getByUserId() {
    }

    @Test
    void get() {
        CafeDto cafe1 = service.save(new Cafe(1, "cafe1", "desc 1", "http://cafe1.com" ), 1);
        CafeDto cafe2 = service.save(new Cafe(1, "cafe2", "desc 2", "http://cafe2.com"), 1);

        getAndPrint(cafe1.getId());
        getAndPrint(cafe2.getId());
        getAndPrint(cafe1.getId());
        getAndPrint(cafe2.getId());


    }

    private void getAndPrint(Integer id) {
        log.info("cafe found: {}", service.get(id, 1));
    }

    @Test
    void save() {
    }

    @Test
    void getMenu() {
    }

    @Test
    void delete() {
    }
}