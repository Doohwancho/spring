package com.tdd.tddTest.mockito.fundamental.verify;

import com.tdd.tddTest.service.ProductService;
import com.tdd.tddTest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.verifyNoInteractions;

@SpringBootTest
public class 메서드_어떤_순서로_호출함 {
    //Q. What

    //verify 메소드를 이용해서
    // 1. 스터빙한 메소드가 실행됐는지,
    // 2. n번 실행됐는지,
    // 3. 실행이 초과되지 않았는지
    // ...등 다양하게 검증해볼 수 있습니다.

    /*****************************************************************************************/
    //case2) 메서드 호출이 어떤 순서대로 호출됨?

    @Mock
    UserService userService;

    @Mock
    ProductService productService;

    @Test
    void testInOrder() {
        // 이 순서로 하면 fail
        // userService.getLoginErrNum();
        // userService.getUser();
        userService.getUser();
        userService.getLoginErrNum();

        InOrder inOrder = inOrder(userService);

        inOrder.verify(userService).getUser();
        inOrder.verify(userService).getLoginErrNum();
    }

    @Test
    void testInOrderWithCalls() {

        // 이 순서로 하면 fail
        // userService.getUser();
        // userService.getLoginErrNum();
        // userService.getUser();

        userService.getUser();
        userService.getUser();
        userService.getLoginErrNum();

        InOrder inOrder = inOrder(userService);

        inOrder.verify(userService, calls(2)).getUser();
        inOrder.verify(userService).getLoginErrNum();
    }

    @Test
    void testInOrderWithVerifyNoMoreInteractions() {
        userService.getUser();
        // userService.getLoginErrNum(); - 실행하면 fail

        InOrder inOrder = inOrder(userService);

        inOrder.verify(userService).getUser();

        verifyNoMoreInteractions(userService); //위에 verify 이후 userService를 호출하면 fail
    }

    @Test
    void testInOrderWithVerifyNoInteractions() {
        userService.getUser();
        userService.getLoginErrNum();
        // productService.getProduct(); - 실행하면 fail

        InOrder inOrder = inOrder(userService);

        inOrder.verify(userService).getUser();
        inOrder.verify(userService).getLoginErrNum();

//        verifyZeroInteractions(productService); //위에 verify 이후 productService를 호출하면 fail
        verifyNoInteractions(productService); //productService를 호출하면 fail
    }
}
