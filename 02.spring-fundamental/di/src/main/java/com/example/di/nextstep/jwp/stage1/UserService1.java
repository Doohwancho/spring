package com.example.di.nextstep.jwp.stage1;

import com.example.di.nextstep.User;

public class UserService1 {
    private final UserDao1 userDao;

    public UserService1(UserDao1 userDao) {
        this.userDao = userDao;
    }

    public User join(User user) {
        userDao.insert(user);
        return userDao.findById(user.getId());
    }
}
