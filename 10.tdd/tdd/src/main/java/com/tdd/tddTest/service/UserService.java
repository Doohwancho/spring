package com.tdd.tddTest.service;

import com.tdd.tddTest.domain.user.User;

public class UserService {

    public User getUser() {
        return new User(1, "user1", 30);
    }

    public int getLoginErrNum() {
        return 1;
    }

    public void deleteUser() {}
}
