package com.example.nextstep.di.stage0;

import com.example.nextstep.User;

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
