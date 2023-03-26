package org.example.com.bbs.util;

import org.example.com.jwt.repository.UserRepository;
import org.example.com.jwt.service.UserService;
import org.example.com.jwt.util.UserTestHelper;
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