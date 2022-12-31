package com.tdd.tddTest.mockito.fundamental.verify;

import com.tdd.tddTest.domain.posts.Posts;
import com.tdd.tddTest.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MoreVerifyExampleTest {

    @Mock
    Posts post;

    @Mock
    User user;

    @Test
    @DisplayName("verify() 검증 테스트")
    public void verifyMockTest(){
        User user = mock(User.class);
        user.setName("doohwan");

        //setName()이 1번 호출했는지 확인
        verify(user, times(1)).setName(any(String.class));

        //setName()호출 시, 파라미터가 doohwan이었는지 확인
//        verify(user, never()).setName("doohwan"); //false
        verify(user, never()).setName("something else");

        //getName()은 한번도 호출 안되었다
        verify(user, never()).getName();

        //setName()이 최소 1번 이상 호출되었는지 확인
        verify(user, atLeastOnce()).setName(any(String.class));

        //setName()이 최대 2번 이하 호출되었는지 확인
        verify(user, atMost(2)).setName(any(String.class));

        //setName()이 지정된 millisecond 안에 호출되었는지 확인
        verify(user, timeout(100)).setName(any(String.class));

        //setName()이 지정된 millisecond 안에 최소 1번 이상 호출되었는지 확인
        verify(user, timeout(100).atLeast(1)).setName(any(String.class));
    }
}
