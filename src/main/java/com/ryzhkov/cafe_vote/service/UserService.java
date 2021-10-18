package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.dto.UserDto;
import com.ryzhkov.cafe_vote.mapper.UserMapper;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNotFound;
import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final UserMapper mapper;

    private final UserRepository repository;

    public User create(@NonNull User user) {
        return checkNotFoundWithId(repository.save(user), user.id());
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    public User get(int id) {
        User user = repository.findById(id).orElse(null);
        return checkNotFoundWithId(user, id);
    }

    public User getByEmail(@NonNull String email) {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    public User update(@NonNull User user) {
        return checkNotFoundWithId(repository.save(user), user.id());
    }
}
