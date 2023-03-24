package com.sp.sec2.util;

import com.sp.sec2.repository.UserRepository;
import com.sp.sec2.service.UserService;
import com.sp.sec2.util.UserTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

public class WithUserTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    public UserRepository userRepository;

    public UserService userService;

    protected UserTestHelper userTestHelper;

    protected void prepareUserService(){
        this.userRepository.deleteAll();
        this.userService = new UserService(mongoTemplate, userRepository);
        this.userTestHelper = new UserTestHelper(userService, NoOpPasswordEncoder.getInstance());
    }
}