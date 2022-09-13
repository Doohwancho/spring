package com.example.nextstep.di.stage4;

import com.example.nextstep.User;

public class UserService4 {
    @Inject
    private UserDao4 userDao;

    public User join(final User user) {
        userDao.insert(user);
        return userDao.findById(user.getId());
    }

    public UserService4() {}
}
