package com.kata.orderinhexagonal.item;

import com.kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ItemValidatorTest {

    @Autowired
    Validator validator;

    @Test
    void 상품명은_null값이면_안된다() {
        //given
        CreateItemRequest request = new CreateItemRequest(null, 1000);
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        assertTrue(errors.hasErrors(), "Expected name as null to be invalid, therefore validation errors were found");
    }

    @ParameterizedTest
    @CsvSource({
            "0, true",
            "1, true",
            "100000000, true",
            "-1, false"
    })
    @DisplayName("이메일 형식 유효여부 확인")
    void 가격_유효여부_확인(int price, boolean expected) {
        //given
        CreateItemRequest request = new CreateItemRequest("name", price);
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        if (expected == true) {
            assertFalse(errors.hasErrors(), "Expected price to be valid, therefore no validation errors were found");
        } else if(expected == false){
            assertTrue(errors.hasErrors(), "Expected price to be invalid, therefore validation errors were found");
        }
    }
}
