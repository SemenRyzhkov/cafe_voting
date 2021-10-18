package com.ryzhkov.cafe_vote.security;


import com.ryzhkov.cafe_vote.model.Status;
import com.ryzhkov.cafe_vote.model.User;

public class SecurityUser {
    public static UserDetailsImpl fromUser(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(), user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}
