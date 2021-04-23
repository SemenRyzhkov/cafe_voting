package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.dto.CafeDto;
import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.mapper.CafeMapper;
import com.ryzhkov.cafe_vote.mapper.DishMapper;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.repository.CafeRepository;
import com.ryzhkov.cafe_vote.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CafeService {
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;
    private final CafeMapper cafeMapper;
    private final DishMapper dishMapper;

    @Cacheable("addresses")
    public List<CafeDto> getAll() {
        return cafeMapper.toDto(cafeRepository.getAll());
    }
    @Cacheable("addresses")
    public List<CafeDto> getByUserId(int userId) {
        return cafeMapper.toDto(cafeRepository.getAllByUserId(userId));
    }

    @Cacheable("addresses")
    public CafeDto get(int id, int userId) {
        log.info("get cafe {} for user {}", id, userId);
        Cafe cafe = cafeRepository.findById(id).orElse(null);
        return cafe != null && cafe.getUser().getId() == userId ? cafeMapper.toDto(cafe) : null;
    }

    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public CafeDto save(@NonNull Cafe cafe, int userId) {
        User user = userRepository.getOne(userId);
        cafe.setUser(user);
        if (cafe.isNew() || get(cafe.getId(), userId) != null) {
            return cafeMapper.toDto(cafeRepository.save(cafe));
        } else return null;
    }

    public List<DishDto> getMenu(int cafeId) {
        return cafeRepository.getOne(cafeId)
                .getMenu().stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public void delete(int id, int userId) {
        cafeRepository.deleteByIdAndUserId(id, userId);
    }
}
