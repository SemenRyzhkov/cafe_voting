package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.mapper.DishMapper;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.Dish;
import com.ryzhkov.cafe_vote.repository.CafeRepository;
import com.ryzhkov.cafe_vote.repository.DishRepository;
import com.ryzhkov.cafe_vote.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DishService {
    private final CafeRepository cafeRepository;
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    public Map<LocalDate, List<DishDto>> historyOfMenu(int cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new NotFoundException("Cafe with id " + cafeId + " not found"));
        return getHistory(cafe);
    }

    public List<DishDto> todayMenuIfPresentOrYesterdayMenu(int cafeId) {
        Cafe cafe = cafeRepository.getWithTodayMenu(cafeId).orElse(null);
        if (cafe == null) {
            cafe = cafeRepository.getWithYesterdayMenu(cafeId)
                    .orElseThrow(() -> new NotFoundException("Cafe with id " + cafeId + " not found"));
        }
        return getMenu(cafe);
    }

    @Transactional
    public DishDto save(@NonNull Dish dish, int userId, int cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId).orElse(null);
        if (cafe != null && cafe.getUser().getId() == userId) {
            dish.setCafe(cafe);
            return dishMapper.toDto(dishRepository.save(dish));
        } else return null;
    }

    public DishDto get(int id, int cafeId) {
        return dishMapper.toDto(dishRepository.getByIdAndCafeId(id, cafeId));
    }

    @Transactional
    public void delete(int id, int cafeId) {
        dishRepository.deleteByIdAndCafeId(id, cafeId);
    }

    private Map<LocalDate, List<DishDto>> getHistory(Cafe cafe) {
        return cafe.getMenu().stream()
                .collect(Collectors.groupingBy(Dish::getDate, Collectors.mapping(dishMapper::toDto, Collectors.toList())));
    }

    private List<DishDto> getMenu(Cafe cafe) {
        return cafe.getMenu().stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
    }
}
