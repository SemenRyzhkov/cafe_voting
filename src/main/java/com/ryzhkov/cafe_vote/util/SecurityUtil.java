package com.ryzhkov.cafe_vote.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SecurityUtil {

    private static int id = 1;

    public static int authUserId() {
        return id;
    }
}