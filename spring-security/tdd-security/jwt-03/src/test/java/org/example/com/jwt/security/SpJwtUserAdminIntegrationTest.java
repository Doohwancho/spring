package org.example.com.jwt.security;

import org.example.com.jwt.service.UserService;
import org.example.com.jwt.util.UserTestHelper;
import org.example.com.jwt.domain.Authority;
import org.example.com.jwt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SpJwtUserAdminIntegrationTest extends SpIntegrationTest {

    @Autowired
    protected UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    protected UserTestHelper userTestHelper;

    protected User USER1;
    protected User ADMIN;

    protected void prepareUserAdmin(){
        userService.clearUsers();
        this.userTestHelper = new UserTestHelper(userService, passwordEncoder);
        this.USER1 = this.userTestHelper.createUser("user1", Authority.ROLE_USER);
        this.ADMIN = this.userTestHelper.createAdmin("admin", Authority.ROLE_ADMIN);
    }

}
