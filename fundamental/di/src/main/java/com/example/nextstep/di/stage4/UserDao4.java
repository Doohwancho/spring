package com.example.nextstep.di.stage4;

import com.example.nextstep.User;

public interface UserDao4 {
    void insert(User user);

    User findById(long id);
}
