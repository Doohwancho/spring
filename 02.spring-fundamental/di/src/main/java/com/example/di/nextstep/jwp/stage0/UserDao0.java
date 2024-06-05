package com.example.di.nextstep.jwp.stage0;

import com.example.di.nextstep.User;

import java.util.HashMap;
import java.util.Map;

class UserDao0 {

    private static final Map<Long, User> users = new HashMap<>();

    public static void insert(User user) {
        users.put(user.getId(), user);
    }

    public static User findById(long id) {
        return users.get(id);
    }
}
