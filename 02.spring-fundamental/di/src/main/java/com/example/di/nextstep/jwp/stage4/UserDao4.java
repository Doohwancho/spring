package com.example.di.nextstep.jwp.stage4;

import com.example.di.nextstep.User;

public interface UserDao4 {
    void insert(User user);

    User findById(long id);
}
