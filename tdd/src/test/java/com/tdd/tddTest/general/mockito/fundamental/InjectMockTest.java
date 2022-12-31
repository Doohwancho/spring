package com.tdd.tddTest.general.mockito.fundamental;

import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.service.OrderService;
import com.tdd.tddTest.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InjectMockTest {

    /*
        ---
        D. @InjectMocks

        @InjectMocks는 @Mock, @Spy가 붙은 객체를 @InjectMocks가 붙은 객체의 필드에 주입해준다.

     */

    @Spy
    ProductService userService;

    @Spy
    ProductService productService;

    @InjectMocks
    OrderService orderService; //@Mock과 @Spy를 주입

    @Test
    void testGetUser() {
        assertNull(orderService.getUser()); //return userService.getUser(); 이므로, userService.getUser()가 null. 왜? @Mock이니까.
    }

    @Test
    void testGetProduct() {
        Product product = orderService.getProduct();

        assertEquals("A001", product.getSerial()); //이건 null반환이 아니다. 왜? @Spy니까.
    }

}
