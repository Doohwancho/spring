package com.example.di.nextstep.stage0;

import com.example.di.nextstep.User;

public class UserService0 {

    public static User join(User user) {
        UserDao0.insert(user);
        return UserDao0.findById(user.getId());
    }
}
