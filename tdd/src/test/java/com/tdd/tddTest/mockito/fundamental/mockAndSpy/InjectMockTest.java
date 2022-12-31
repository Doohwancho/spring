package com.tdd.tddTest.mockito.fundamental.mockAndSpy;

import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.service.OrderService;
import com.tdd.tddTest.service.ProductService;
import com.tdd.tddTest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class InjectMockTest {

    /*
        ---
        D. @InjectMocks

        @InjectMocks는 @Mock, @Spy가 붙은 객체를 @InjectMocks가 붙은 객체의 필드에 주입해준다.

     */

    @Mock
    UserService userService;

    @Spy
    ProductService productService;

    @InjectMocks
    OrderService orderService; //@Mock과 @Spy를 주입

    @Test
    void testGetUser() {
        assertNull(orderService.getUser()); //return userService.getUser(); 이므로, userService.getUser()가 null. 왜? @Mock ProductService 이니까.
//        assertNull(orderService.getProduct()); //return productService.getProduct(); 이므로, productService.getProduct()가 null. 왜? @Spy ProductService 이니까.
    }

    @Test
    void testGetProduct() {
        Product product = orderService.getProduct();

        assertEquals("A001", product.getSerial()); //이건 null반환이 아니다. 왜? @Spy니까.
    }
}
