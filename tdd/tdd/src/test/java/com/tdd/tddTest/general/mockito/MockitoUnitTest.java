package com.tdd.tddTest.general.mockito;

import com.tdd.tddTest.domain.posts.Posts;
import com.tdd.tddTest.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertFalse;

public class MockitoUnitTest {

    /*
        ---
        pre-requisites

        A. Most Common Java Exceptions

        NullPointerException.
        ArrayIndexOutOfBoundsException.
        IllegalStateException.
        ClassCastException.
        ArithmeticException.
        IllegalArgumentException.

     */


    @Mock
    Posts post;

    @Mock
    User user;

    @Test
    @DisplayName("stub - when().thenX()")
    public void makeStubTest(){
//        MockitoAnnotations.initMocks(this);
        Posts post = mock(Posts.class);
        assertTrue(post != null);

        //when().thenReturn()
        when(post.getTitle()).thenReturn("mocked-title");
        when(post.getContent()).thenReturn("mocked-content");
        when(post.getAuthor()).thenReturn("mocked-author");

        assertTrue(post.getTitle().equals("mocked-title"));
        assertTrue(post.getContent().equals("mocked-content"));
        assertTrue(post.getAuthor().equals("mocked-author"));


        //when().thenThrow()
        User user = mock(User.class);
        assertTrue(user != null);

        when(user.setUserLevel(anyInt())).thenThrow(new IllegalStateException("you are not supposed to set user id"));
//        doThrow(IllegalStateException.class).when(user).setUserLevel(anyInt());

        assertThrows(IllegalStateException.class, () -> {
            user.setUserLevel(100);
        });
    }


    @Test
    @DisplayName("doThrow() test")
    public void doThrowTest(){
        Posts post = mock(Posts.class);
        assertTrue(post != null);

        doThrow(IllegalStateException.class).when(post).getCreatedDate(); //new RuntimeException()

        assertThrows(IllegalStateException.class, () -> {
            post.getCreatedDate();
        });
    }


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

    @Test
    @DisplayName("spying 테스트")
    public void spyingMockTest(){
        List spyList = spy(ArrayList.class);

        spyList.add("apple");
        //@Mock 객체는 껍데기 뿐이라 .get()호출하면 에러나는 반면, spying 객체는 실제 객체처럼 동작한다.
        assertEquals("apple", spyList.get(0));

        //spying 객체는 when()도 먹히고
        when(spyList.indexOf("stub1")).thenReturn(1);
        doReturn("stub2").when(spyList).get(2); //객체에 2번째 엘레멘트 없으면, .thenReturn()쓰지 말고 doReturn()먼저 쓰고 .when() 더해주면 해결
        assertEquals(1, spyList.indexOf("stub1"));
        assertEquals("stub2", spyList.get(2));

        //spying 객체는 verify()도 먹힌다.
        verify(spyList, never()).get(1);

        /*
            어라? Spy는 Mock과 똑같은데? 왜 씀?

            Mock은 빈 객체에서 내가 원하는 소수 기능에 when().thenX() 지정해서 쓰는거고,
            spy는 역할이 많아서 그 역할을 다 when().thenX()하기 곤란한 데, 소수 기능만 stub해야할 때 쓴다.
         */

    }
}
