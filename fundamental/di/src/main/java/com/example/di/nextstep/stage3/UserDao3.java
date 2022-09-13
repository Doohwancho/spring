package com.example.di.nextstep.stage3;

import com.example.di.nextstep.User;

public interface UserDao3 {
    void insert(User user);

    User findById(long id);
}
