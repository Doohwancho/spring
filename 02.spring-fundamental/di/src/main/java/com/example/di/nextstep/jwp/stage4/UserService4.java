package com.example.di.nextstep.jwp.stage4;

import com.example.di.nextstep.User;

public class UserService4 {
    @Inject
    private UserDao4 userDao;

    public User join(final User user) {
        userDao.insert(user);
        return userDao.findById(user.getId());
    }

    public UserService4() {}
}
