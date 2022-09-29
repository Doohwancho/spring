package com.example.di.nextstep.jwp.stage3;

import com.example.di.nextstep.User;

public class UserService3 {
    private UserDao3 userDao;

    public UserService3(final UserDao3 userDao) {
        this.userDao = userDao;
    }

    public UserService3() {}

    public User join(final User user) {
        userDao.insert(user);
        return userDao.findById(user.getId());
    }
}
