package com.ryzhkov.cafe_vote;

import com.ryzhkov.cafe_vote.model.Role;
import com.ryzhkov.cafe_vote.model.User;

import java.util.Collections;


public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class,  "cafes");

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User user = new User(USER_ID, "User_First", "user@gmail.com", "user", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin_First", "admin@yandex.ru", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass",  Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
