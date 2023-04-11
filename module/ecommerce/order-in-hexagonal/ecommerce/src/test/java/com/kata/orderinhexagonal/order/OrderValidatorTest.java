package com.kata.orderinhexagonal.order;

import com.kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import com.kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class OrderValidatorTest {

    @Autowired
    Validator validator;

    @Test
    void 주문요청에_주문자_아이디가_반드시_포함되어_있어야_한다() {
        //given
        List<OrderItemRequest> orderItemRequests = new ArrayList<>();
        orderItemRequests.add(OrderItemRequest.of(1L,1));
        orderItemRequests.add(OrderItemRequest.of(2L, 2));

        CreateOrderRequest request = CreateOrderRequest.of(orderItemRequests);
        request.assignOrdererId(null);

        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        assertTrue(errors.hasErrors(), "주문자 ID가 없습니다.");
    }

    @Test
    void 주문요청에_주문상품이_반드시_하나_이상_포함되어_있어야_한다() {
        //given
        List<OrderItemRequest> orderItemRequests = new ArrayList<>();

        CreateOrderRequest request = CreateOrderRequest.of(orderItemRequests);
        request.assignOrdererId(1L);

        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        assertTrue(errors.hasErrors(), "주문할 상품을 추가해 주세요.");
    }


    @Test
    void 주문요청에_주문상품의_아이디가_반드시_있어야_한다() {
        //given
        OrderItemRequest request = new OrderItemRequest(null, 1);
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        assertTrue(errors.hasErrors(), "주문할 상품의 ID가 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "null, false",
            "-1, false",
            "0, false",
            "1, true",
            "100000000, true"
    }, nullValues={"null"})
    void 주문요청에_주문상품의_주문수량이_반드시_1개_이상_있어야_한다(Integer quantity, boolean expected) {
        //given
        OrderItemRequest request = new OrderItemRequest(1L, quantity);
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        if (expected == true) {
            assertFalse(errors.hasErrors(), "Expected order quantity to be valid, therefore no validation errors were found");
        } else if(expected == false){
            if(quantity == null) {
                assertTrue(errors.hasErrors(), "주문할 상품의 개수를 입력해주세요.");
            } else {
                assertTrue(errors.hasErrors(), "주문할 상품의 개수를 1개 이상으로 입력해주세요.");
            }
        }
    }
}
