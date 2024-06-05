package com.cho.security.service;

import com.cho.security.model.UserVO;

public interface UserService {

    UserVO login(UserVO userVO);

    UserVO createUser(UserVO userVO);

    UserVO findUserByUserEmail(String userEmail);

}
