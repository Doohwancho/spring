package com.example.nextstep.di.stage0;

import com.example.nextstep.User;

public class UserService1 {

    public static User join(User user) {
        UserDao1.insert(user);
        return UserDao1.findById(user.getId());
    }
}
