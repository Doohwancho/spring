package com.sp.sec2;

import com.sp.sec2.domain.Authority;
import com.sp.sec2.domain.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@DataMongoTest
public class UserAuthorityJpaTest extends WithUserTest {

    @BeforeEach
    void before(){
        prepareUserService();
    }

    @DisplayName("1. 사용자를 생성한다.")
    @Test
    void test_1() throws DuplicateKeyException {
        userTestHelper.createUser("user1");
        List<User> userList = this.userRepository.findAll();

        assertEquals(1, userList.size());
        userTestHelper.assertUser(userList.get(0), "user1");
    }

    @DisplayName("2. 사용자의 이름을 수정한다.")
    @Test
    void test_2() throws DuplicateKeyException {
        User user1 = userTestHelper.createUser("user1");
        userService.updateUserName(user1.getUserId(), "user2");

        User savedUser = userService.findUser(user1.getUserId()).get();
        assertEquals("user2", savedUser.getName());
    }

    //TODO - j-b-3: user authority test 방법론

    @Disabled("authority를 추가하는 부분에서 문제가 발생한다. string -> SimpleGrantedAuthority로 변환해야 한다.")
    @DisplayName("3. authority를 부여한다.")
    @Test
    void test_3() throws DuplicateKeyException {
        User user1 = userTestHelper.createUser("user1", Authority.ROLE_USER);
        userService.addAuthority(user1.getUserId(), Authority.ROLE_ADMIN);
        User savedUser = userService.findUser(user1.getUserId()).get();
        userTestHelper.assertUser(savedUser, "user1", Authority.ROLE_USER, Authority.ROLE_ADMIN);
    }


    @Disabled("authority를 추가하는 부분에서 문제가 발생한다. string -> SimpleGrantedAuthority로 변환해야 한다.")
    @DisplayName("4. authority를 뺏는다.")
    @Test
    void test_4() throws DuplicateKeyException {
        User user1 = userTestHelper.createUser("admin", Authority.ROLE_USER, Authority.ROLE_ADMIN);
        userService.removeAuthority(user1.getUserId(), Authority.ROLE_USER);
        User savedUser = userService.findUser(user1.getUserId()).get();
        userTestHelper.assertUser(savedUser, "admin", Authority.ROLE_ADMIN);
    }

    @DisplayName("5. email 로 검색이 된다.")
    @Test
    void test_5() throws DuplicateKeyException {
        User user1 = userTestHelper.createUser("user1");
        User saved = (User) userService.loadUserByUsername("user1@test.com");
        userTestHelper.assertUser(saved, "user1");
    }

    @Disabled("authority를 추가하는 부분에서 문제가 발생한다. string -> SimpleGrantedAuthority로 변환해야 한다.")
    @DisplayName("6. role이 중복되서 추가되지 않는다.")
    @Test
    void test_6() throws DuplicateKeyException {
        User user1 = userTestHelper.createUser("user1", Authority.ROLE_USER);
        userService.addAuthority(user1.getUserId(), Authority.ROLE_USER);
        userService.addAuthority(user1.getUserId(), Authority.ROLE_USER);
        User savedUser = userService.findUser(user1.getUserId()).get();
        userTestHelper.assertUser(savedUser, "user1", Authority.ROLE_USER);
    }

    @DisplayName("7. email이 중복되어서 들어가는가?")
    @Test
    void test_() throws DuplicateKeyException {
        userTestHelper.createUser("user1");
        assertThrows(DuplicateKeyException.class, ()->{
            userTestHelper.createUser("user1");
        });
    }

}