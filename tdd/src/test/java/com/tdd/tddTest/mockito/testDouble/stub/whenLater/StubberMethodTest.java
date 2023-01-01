package com.tdd.tddTest.mockito.testDouble.stub.whenLater;

import com.tdd.tddTest.domain.user.User;
import com.tdd.tddTest.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;

import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class StubberMethodTest {

    //Q. 왜 when().thenReturn()이나 when().thenThrow()를 하지, doReturn().when() 이나 doThrow().when()함?
    //A. when()에서 에러날 수 있으니까, 먼저 doThrow로 Exception 정의해주는 것

    @Mock
    UserService userService;

    @Test
    void testThenReturn() {
        //--Ex1)
        List list = new LinkedList();
        List spiedList = spy(list);

//        when(spy.get(0)).thenReturn("foo"); //error! list is empty object!

        //위와 같은 경우를 doReturn을 사용한다.
        doReturn("foo").when(spiedList).get(0);


        //--Ex2)
        when(userService.getLoginErrNum()).thenThrow(new RuntimeException());

        //불가능 : 이미 mock.foo()를 호출할 때 RuntimeException이 일어나기 때문에 bar를 리턴할 수 없음
//        when(userService.getLoginErrNum()).thenReturn(1); //error!

        //위와 같은 경우를 doReturn을 사용한다.
        doReturn(1).when(userService).getLoginErrNum();
    }

    /*****************************************************************************************/

    //more examples

//    @Mock
//    UserService userService; //already defined

    @Test
    void testDoReturn() {
        User user = new User(1, "Peter", 30);

        doReturn(user).when(userService).getUser();

        assertThat(userService.getUser()).isEqualTo(user);
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException()).when(userService).deleteUser();

        assertThatThrownBy(() -> userService.deleteUser()).isInstanceOf(RuntimeException.class);
    }


    /*****************************************************************************************/

    //method chaining
    //Exception error 처리할 때 쓰는건가봄.

    @Mock
    ProductService productService;

    @Test
    void testConsecutiveStubbing() {
        Product product = new Product("D001","water");

        when(productService.getProduct())
                .thenReturn(product)
                .thenThrow(new RuntimeException());

        //첫 번째 호출 : .thenReturn(product)
        assertThat(productService.getProduct()).isEqualTo(product);

        //두 번째 호출 : .thenThrow(new RuntimeException());
        assertThatThrownBy(() -> productService.getProduct()).isInstanceOf(RuntimeException.class);
    }

}
