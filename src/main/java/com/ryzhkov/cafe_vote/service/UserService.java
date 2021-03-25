package com.ryzhkov.cafe_vote.service;

import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNotFound;
import static com.ryzhkov.cafe_vote.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class UserService {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    public User create(@NonNull User user) {
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        User user = repository.findById(id).orElse(null);
        return checkNotFoundWithId(user, id);
    }

    public User getByEmail(@NonNull String email) {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    public User update(@NonNull User user) {
        return checkNotFoundWithId(repository.save(user), user.id());
    }
}
