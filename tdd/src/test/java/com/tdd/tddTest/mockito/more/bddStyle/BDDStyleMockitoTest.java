package com.tdd.tddTest.mockito.more.bddStyle;

import com.tdd.tddTest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


/*
---
what is BDD?

TDD (Test Driven Development)는 "테스트"를 기준으로 하는 개발 방법론입니다.
BDD (Behavior Driven Development)는 "행위"를 기준으로 하는 개발 방법론입니다.


Given: the initial context at the beginning of the scenario, in one or more clauses;
When: the event that triggers the scenario
Then: the expected outcome, in one or more clauses.
*/

@SpringBootTest
public class BDDStyleMockitoTest {

    @Mock
    UserService userService;

    @Test
    void testVerifyTimes() {
        //given
        //when -> given, when(userService.getUser()).thenReturn(null);
        given(userService.getUser()).willReturn(null);

        //when
        userService.getUser();
        userService.getUser();

        //then
        //verify -> then, verify(userService, times(2)).getUser();
        then(userService).should(times(2)).getUser();
    }
}
