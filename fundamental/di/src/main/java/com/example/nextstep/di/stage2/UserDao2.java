package com.example.nextstep.di.stage2;

import com.example.nextstep.User;

public interface UserDao2 {
    void insert(User user);

    User findById(long id);
}
