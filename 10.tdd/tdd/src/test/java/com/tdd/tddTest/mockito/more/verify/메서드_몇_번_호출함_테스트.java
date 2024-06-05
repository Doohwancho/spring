package com.tdd.tddTest.mockito.more.verify;

import com.tdd.tddTest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class 메서드_몇_번_호출함_테스트 {


    //Q. What

    //verify 메소드를 이용해서
    // 1. 스터빙한 메소드가 실행됐는지,
    // 2. n번 실행됐는지,
    // 3. 실행이 초과되지 않았는지
    // ...등 다양하게 검증해볼 수 있습니다.

    /*****************************************************************************************/
    //case1) 몇번 호출함?

    @Mock
    UserService userService;

    @Test
    void testVerifyTimes() {
        userService.getUser();
        userService.getUser();

        verify(userService, times(2)).getUser();
    }

    @Test
    void testVerifyNever() {
        verify(userService, never()).getUser();
    }

    @Test
    void testAtLeastOne() {
        userService.getUser();

        verify(userService, atLeastOnce()).getUser();
    }

    @Test
    void testAtLeast() {
        //한 번만 실행하면 fail
        userService.getUser();
        userService.getUser();
        userService.getUser();

        verify(userService, atLeast(2)).getUser();
    }

    @Test
    void testAtMostOnce() {
        userService.getUser();
        // userService.getUser(); - 2번 이상 실행하면 fail

        verify(userService, atMostOnce()).getUser();
    }

    @Test
    void testAtMost() {
        userService.getUser();
        userService.getUser();
        userService.getUser();
        // userService.getUser(); - 6번 이상 실행하면 fail

        verify(userService, atMost(3)).getUser();
    }

    @Test
    void testOnly() {
        userService.getUser();
        // userService.getLoginErrNum(); - getUser()가 아닌 다른 메소드 실행 시 fail

        verify(userService, only()).getUser();
    }
}
