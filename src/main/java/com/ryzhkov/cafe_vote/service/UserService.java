package com.ryzhkov.cafe_vote.service;


import com.ryzhkov.cafe_vote.dto.userDto.AdminDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserDto;
import com.ryzhkov.cafe_vote.dto.userDto.UserEditDto;
import com.ryzhkov.cafe_vote.mapper.UserMapper;
import com.ryzhkov.cafe_vote.model.Role;
import com.ryzhkov.cafe_vote.model.Status;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.repository.CafeRepository;
import com.ryzhkov.cafe_vote.repository.UserRepository;
import com.ryzhkov.cafe_vote.security.SecurityUser;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");
    private final PasswordEncoder bCryptPasswordEncoder;
    private final CafeRepository cafeRepository;
    private final UserRepository repository;
    private final UserMapper mapper;


    @Autowired
    public UserService(PasswordEncoder bCryptPasswordEncoder, CafeRepository cafeRepository, UserRepository repository, UserMapper mapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cafeRepository = cafeRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto get(int id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with id %d does not exist", id))));
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with email %s does not exist", email)));
        return SecurityUser.fromUser(user);
    }

    public UserDto create(@NonNull UserEditDto userDto) {
        User newUser = mapper.dtoToEntity(userDto);
        newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setStatus(Status.ACTIVE);
        User savedUser;
        try {
            savedUser = repository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Email already exist");
        }
        return mapper.toDto(savedUser);
    }

    @Transactional
    public UserDto update(@NonNull UserEditDto userDto, int id) {
        User user = repository.getOne(id);
        mapper.patchFromEditDto(userDto, user);
        User updatedUser;
        try {
            updatedUser = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Email already exist");
        }
        return mapper.toDto(updatedUser);
    }

    @Transactional
    public UserDto updateForAdmin(AdminDto dto, int id) {
        User user = repository.getOne(id);
        mapper.patchFromAdminDto(dto, user);
        return mapper.toDto(repository.save(user));
    }

    public void delete(int id) {
        cafeRepository.deleteByUserId(id);
        repository.deleteById(id);
    }
}
