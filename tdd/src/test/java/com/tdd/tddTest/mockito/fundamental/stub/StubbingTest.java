package com.tdd.tddTest.mockito.fundamental.stub;

import com.tdd.tddTest.domain.posts.Posts;
import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.domain.user.User;
import com.tdd.tddTest.service.ProductService;
import com.tdd.tddTest.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StubbingTest {


/*
        ---
        B. Stubbing

        what?
        mock 객체의 메소드를 실행했을 때 어떤 리턴 값을 리턴할지를 정의하는 것

        ex.
        Mock Object만들어서 반환하면, primitive type은 0, reference type은 null이 반환된다.
        Mock Object는 가짜객체니까.
        따라서 Mock 객체 안 메서드 호출하려면 Stubbing 해줘서 내가 원하는 Output을 출력할 수 있어야 한다.


        ex. when(productService.getProduct()).thenReturn(productDummy);

 */

    @Mock
    UserService userService;

    //why need stubbing?
    //because mock object return 0 or null when method called.
    @Test
    void testReferenceType() {
        assertNull(userService.getUser()); // mock object's method returns null
    }

    @Test
    void testPrimitiveType() {
        assertEquals(0, userService.getLoginErrNum()); // mock object's method returns 0, even though real method returns 1.
    }


    /*****************************************************************************/

    //stubbing example

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
}
