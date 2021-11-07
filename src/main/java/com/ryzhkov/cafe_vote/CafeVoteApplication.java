package com.ryzhkov.cafe_vote;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CafeVoteApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(CafeVoteApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

}
