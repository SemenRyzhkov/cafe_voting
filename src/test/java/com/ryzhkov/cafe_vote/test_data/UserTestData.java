package com.ryzhkov.cafe_vote.test_data;

import com.ryzhkov.cafe_vote.TestMatcher;
import com.ryzhkov.cafe_vote.dto.userDto.UserDto;
import com.ryzhkov.cafe_vote.model.Role;
import com.ryzhkov.cafe_vote.model.Status;
import com.ryzhkov.cafe_vote.model.User;

import java.util.Collections;


public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class,  "cafes");
    public static final TestMatcher<UserDto> USER_DTO_MATCHER = TestMatcher.usingIgnoringFieldsComparator(UserDto.class,  "password");

    public static final int ADMIN_ID = 1;
    public static final int USER_ID = 3;

    public static final User user = new User(USER_ID, "User_First", "user", "user@gmail.com", Role.USER, Status.ACTIVE);
    public static final User user2 = new User(USER_ID + 1, "User_Second", "second", "userSec@gmail.com", Role.USER, Status.BANNED);
    public static final User admin1 = new User(ADMIN_ID, "Admin_First", "admin", "admin@yandex.ru", Role.ADMIN, Status.ACTIVE);
    public static final User admin2 = new User(ADMIN_ID+1, "Admin_Second", "second", "second@yandex.ru", Role.ADMIN, Status.ACTIVE);

    public static User getNew() {
        return new User(null, "New", "newPass", "new@gmail.com", Role.USER, Status.ACTIVE);
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setRole(Role.USER);
        return updated;
    }

    public static User getUpdatedAdmin() {
        User updated = new User(user);
        updated.setRole(Role.ADMIN);
        updated.setStatus(Status.BANNED);
        return updated;
    }
}
