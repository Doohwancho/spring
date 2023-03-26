package org.example.jwt.security;

import org.example.jwt.domain.Authority;
import org.example.jwt.domain.User;
import org.example.jwt.service.UserService;
import org.example.jwt.util.UserTestHelper;
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
