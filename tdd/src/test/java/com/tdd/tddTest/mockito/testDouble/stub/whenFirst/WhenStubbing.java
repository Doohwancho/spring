package com.tdd.tddTest.mockito.testDouble.stub.whenFirst;

import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WhenStubbing {

    /*
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
     */

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

    //use .thenReturn(), .thenThrow() instead. not recommended from official docs.
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

}
