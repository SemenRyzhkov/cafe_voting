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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;
    private final CafeMapper cafeMapper;
    private final DishMapper dishMapper;

    public List<CafeDto> getAll() {
        return cafeRepository.getAll()
                .stream()
                .map(cafeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CafeDto> getByUserId(int userId) {
        return cafeRepository.getByUserId(userId)
                .stream()
                .map(cafeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CafeDto get(int id, int userId) {
        Cafe cafe = cafeRepository.findById(id).orElse(null);
        return cafe != null && cafe.getUser().getId() == userId ? cafeMapper.toDto(cafe) : null;
    }

    @Transactional
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
    public void delete(int id, int userId) {
        cafeRepository.deleteByIdAndUserId(id, userId);
    }
}
