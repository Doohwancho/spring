package com.example.di.nextstep.jwp.stage2;

import com.example.di.nextstep.User;

public class UserService2 {
    private final UserDao2 userDao;

    public UserService2(UserDao2 userDao) {
        this.userDao = userDao;
    }

    public User join(User user) {
        userDao.insert(user);
        return userDao.findById(user.getId());
    }
}