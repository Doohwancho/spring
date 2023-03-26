package org.example.com.bbs.util;

import org.example.com.security.domain.Authority;
import org.example.com.security.domain.User;
import org.example.com.security.config.SpIntegrationTest;
import org.example.com.security.service.UserService;
import org.example.com.security.util.UserTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SpJwtTwoUserIntegrationTest extends SpIntegrationTest {

    @Autowired
    protected UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    protected UserTestHelper userTestHelper;

    protected User USER1;
    protected User USER2;

    protected void prepareTwoUsers(){
        userService.clearUsers();
        this.userTestHelper = new UserTestHelper(userService, passwordEncoder);
        this.USER1 = this.userTestHelper.createUser("user1", Authority.ROLE_USER);
        this.USER2 = this.userTestHelper.createUser("user2", Authority.ROLE_USER);
    }

}
