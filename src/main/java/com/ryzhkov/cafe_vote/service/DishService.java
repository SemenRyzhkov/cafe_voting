package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.mapper.DishMapper;
import com.ryzhkov.cafe_vote.model.Cafe;
import com.ryzhkov.cafe_vote.model.Dish;
import com.ryzhkov.cafe_vote.repository.CafeRepository;
import com.ryzhkov.cafe_vote.repository.DishRepository;
import com.ryzhkov.cafe_vote.util.exception.NotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishService {
    private final CafeRepository cafeRepository;
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Autowired
    public DishService(CafeRepository cafeRepository, DishRepository dishRepository, DishMapper dishMapper) {
        this.cafeRepository = cafeRepository;
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper;
    }

    public TreeMap<LocalDate, List<DishDto>> historyOfMenu(int cafeId, int userid) {
        Cafe cafe = cafeRepository.findByIdWithMenu(cafeId)
                .orElseThrow(() -> new NotFoundException("Cafe with id " + cafeId + " not found"));
        if (cafe != null && cafe.getUser().getId() == userid) {
            return getHistory(cafe);
        } else return null;
    }

    public List<DishDto> menuByDate(int cafeId, int userId, LocalDate date) {
        Cafe cafe = cafeRepository.getWithMenuByDate(cafeId, date)
                .orElseThrow(() -> new NotFoundException("Cafe with id " + cafeId + " not found"));
        if (cafe != null && cafe.getUser().getId() == userId) {
            return cafe.getMenu().stream()
                    .map(dishMapper::toDto)
                    .collect(Collectors.toList());
        } else return null;
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
    public DishDto save(@NonNull DishDto dish, int userId, int cafeId) {
        Dish savedDish = dishMapper.toEntity(dish);
        Cafe cafe = getCafe(cafeId);
        if (cafe != null && cafe.getUser().getId() == userId) {
            if (dish.isNew()) {
                savedDish.setDate(LocalDate.now());
            } else {
                savedDish.setDate(get(dish.getId(), cafeId).getDate());
            }
            savedDish.setCafe(cafe);
            return dishMapper.toDto(dishRepository.save(savedDish));
        } else return null;
    }

    @Transactional
    public void delete(int id, int userId, int cafeId) {
        Cafe cafe = getCafe(cafeId);
        if (cafe != null && cafe.getUser().getId() == userId) {
            dishRepository.deleteByIdAndCafeId(id, cafeId);
        }
    }

    private Cafe getCafe(int cafeId) {
        return cafeRepository.findById(cafeId).orElse(null);
    }

    public Dish get(int id, int cafeId) {
        return dishRepository.getByIdAndCafeId(id, cafeId).orElseThrow(() -> new NotFoundException("Dish not found"));
    }

    private TreeMap<LocalDate, List<DishDto>> getHistory(Cafe cafe) {
        TreeMap<LocalDate, List<DishDto>> history = new TreeMap<>(cafe.getMenu().stream()
                .collect(Collectors.groupingBy(Dish::getDate, Collectors.mapping(dishMapper::toDto, Collectors.toList()))));
        history.values().forEach(list -> list.sort(Comparator.comparing(DishDto::getDish)));
        return history;
    }


    private List<DishDto> getMenu(Cafe cafe) {
        return cafe.getMenu().stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
    }
}
