package com.tdd.tddTest.general.mockito.fundamental.stub;

import com.tdd.tddTest.domain.posts.Posts;
import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.domain.user.User;
import com.tdd.tddTest.service.ProductService;
import com.tdd.tddTest.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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



        ---
        How?

        when().thenReturn()
            - when()은 mock 객체의 메서드를 호출하고, thenReturn()은 mock 객체의 메서드가 리턴할 값을 정의한다.
        when().thenAnswer()
            - when()은 mock 객체의 메서드를 호출하고, thenAnswer()는 스터빙한 메소드 호출 후 어떤 작업을 할지 custom하게 정의.
            - not recommended from official docs. use .thenReturn(), .thenThrow() instead.
        when().thenThrow()
            - when()은 mock 객체의 메서드를 호출하고, thenThrow()는 mock 객체의 메서드가 발생시킬 예외를 정의한다.
        when().thenCallRealMethod()
            - 실제 메서드 호출을 한다. (mock 객체가 아닌 실제 객체를 호출한다.)
            - spy 객체를 사용할 때 사용한다.


        ex. when(productService.getProduct()).thenReturn(productDummy);


 */

    @Mock
    UserService userService;

    @Test
    void testReferenceType() {
        assertNull(userService.getUser()); // mock object's method returns null
    }

    @Test
    void testPrimitiveType() {
        assertEquals(0, userService.getLoginErrNum()); // mock object's method returns 0, even though real method returns 1.
    }


    /*****************************************************************************/


    @Mock
    ProductService productService;

    @Test
    void testThenReturn() {
        Product product = new Product("T001", "mouse");

        when(productService.getProduct()).thenReturn(product);

        assertThat(productService.getProduct()).isEqualTo(product);
    }

    @Test
    void testThenThrows() {
        when(productService.getProduct()).thenThrow(new IllegalArgumentException());

        assertThatThrownBy(() -> productService.getProduct()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testThenAnswer() {
        when(productService.getProduct(any(), any())).thenAnswer((Answer) invocation -> {
            Object[] args = invocation.getArguments();

            return new Product(args[0] + "1", args[1] + "_1233");
        });

        assertThat(productService.getProduct("S001","desk").getSerial()).isEqualTo("S0011");
    }

    @Test
    void testThenCallRealMethod() {
        when(productService.getProduct()).thenCallRealMethod();

        assertThat(productService.getProduct().getSerial()).isEqualTo("A001");
    }



    /*****************************************************************************/


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
