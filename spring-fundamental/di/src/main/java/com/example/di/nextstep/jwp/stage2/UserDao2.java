package com.example.di.nextstep.jwp.stage2;

import com.example.di.nextstep.User;

public interface UserDao2 {
    void insert(User user);

    User findById(long id);
}
