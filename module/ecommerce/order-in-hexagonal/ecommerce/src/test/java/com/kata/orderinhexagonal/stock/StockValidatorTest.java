package com.kata.orderinhexagonal.stock;

import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StockValidatorTest {

    @Autowired
    Validator validator;

    @Test
    void 상품ID가_null이면_안된다() {
        //given
        StockInRequest request = new StockInRequest(null, 10);
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        assertTrue(errors.hasErrors(), "Expected item id as null to be invalid, therefore validation errors were found");
    }

    @ParameterizedTest
    @CsvSource({
            "1, true",
            "100000000, true",
            "0, false",
            "-1, false"
    })
    void 재고_수량은_1개_이상이어야_한다(int quantity, boolean expected) {
        //given
        StockInRequest request = new StockInRequest(1L, quantity);
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        if (expected == true) {
            assertFalse(errors.hasErrors(), "Expected quantity to be valid, therefore no validation errors were found");
        } else if(expected == false){
            Contracts.assertTrue(errors.hasErrors(), "Expected quantity to be invalid, therefore validation errors were found");
        }
    }
}
