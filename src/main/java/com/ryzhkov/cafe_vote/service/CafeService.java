package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.mapper.CafeMapper;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.repository.CafeRepository;
import com.ryzhkov.cafe_vote.repository.UserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CafeService {
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;
    private final CafeMapper cafeMapper;

    @Autowired
    public CafeService(CafeRepository cafeRepository, UserRepository userRepository, CafeMapper cafeMapper) {
        this.cafeRepository = cafeRepository;
        this.userRepository = userRepository;
        this.cafeMapper = cafeMapper;
    }

    @Cacheable("cafes")
    public List<CafeDto> getAll() {
        return cafeRepository.getAll()
                .stream()
                .map(cafeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Cacheable("cafes")
    public List<CafeDto> getByUserId(int userId) {
        return cafeRepository.getAllByUserId(userId)
                .stream()
                .map(cafeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Cacheable("cafes")
    public CafeDto get(int id, int userId) {
        log.info("get cafe {} for user {}", id, id);
        Cafe cafe = cafeRepository.findByIdAndUserId(id, userId);
        return cafeMapper.toDto(cafe);
    }

    @Transactional
    @CacheEvict(value = "cafes", allEntries = true)
    public CafeDto save(@NonNull CafeDto cafeDto, int userId) {
        Cafe cafe = cafeMapper.toEntity(cafeDto);
        User user = userRepository.getOne(userId);
        cafe.setUser(user);
        return cafeMapper.toDto(cafeRepository.save(cafe));
    }

    @Transactional
    @CacheEvict(value = "cafes", allEntries = true)
    public CafeDto update(@NonNull CafeDto cafeDto, int id, int userId) {
        User user = userRepository.getOne(userId);
        Cafe cafe = cafeRepository.findByIdAndUserId(id, userId);
        cafeMapper.patchFromDto(cafeDto, cafe);
        cafe.setUser(user);
        cafe.setId(id);
        return cafeMapper.toDto(cafeRepository.save(cafe));
    }


    @Transactional
    @CacheEvict(value = "cafes", allEntries = true)
    public void delete(int id, int userId) {
        cafeRepository.deleteByIdAndUserId(id, userId);
    }
}
