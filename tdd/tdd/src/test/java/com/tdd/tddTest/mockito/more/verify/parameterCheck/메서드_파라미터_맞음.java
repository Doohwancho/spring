package com.tdd.tddTest.mockito.more.verify.parameterCheck;

import com.tdd.tddTest.domain.posts.Posts;
import com.tdd.tddTest.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class 메서드_파라미터_맞음 {

    @Mock
    Posts post;

    @Mock
    User user;

    @Test
    @DisplayName("mockito로 param 확인 테스트")
    public void argumentCaptorMockTest(){
        List mockList = mock(ArrayList.class);
        mockList.add("apple");
//        mockList.add(1);

        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class); //ArgumentCaptor: mock에 전달된 인자 확인 용도
        verify(mockList).add(arg.capture()); //mockList에 .add(param); 될 떄의 파라미터 값 추출.
        assertEquals("apple", arg.getValue());
    }


}
