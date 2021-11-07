package com.ryzhkov.cafe_vote.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CaffeineCacheConfiguration {
    @Value("${spring.cache.caffeine.cache-names:cafes}")
    private String[] cacheNames;
    @Value("${spring.cache.caffeine.initial-capacity:500}")
    private Integer initialCapacity;
    @Value("${spring.cache.caffeine.maximum-size:1000}")
    private Integer maximumSize;
    @Value("${spring.cache.caffeine.expire-after-access:300}")
    private Integer expireAfterAccess;

    @Bean
    public CacheManager cacheManager() {
        final CaffeineCacheManager cacheManager = new CaffeineCacheManager(cacheNames);

        cacheManager.setCaffeine(caffeineCacheBuilder());

        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize)
                .expireAfterAccess(expireAfterAccess, TimeUnit.SECONDS)
                .recordStats();
    }
}

