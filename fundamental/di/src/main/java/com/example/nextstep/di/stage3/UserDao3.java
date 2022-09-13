package com.example.nextstep.di.stage3;

import com.example.nextstep.User;

public interface UserDao3 {
    void insert(User user);

    User findById(long id);
}
