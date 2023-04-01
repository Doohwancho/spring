package com.kata.orderinhexagonal.discount;

import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountResponse;
import com.kata.orderinhexagonal.discount.application.port.out.DiscountItemLoadPort;
import com.kata.orderinhexagonal.discount.application.port.out.DiscountPolicyValidator;
import com.kata.orderinhexagonal.discount.domain.Discount;
import com.kata.orderinhexagonal.discount.domain.DiscountType;
import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscountValidatorTest {

    @Autowired
    DiscountPolicyValidator discountPolicyValidator;
    @Autowired
    ItemFixture itemFixture;

    @Test
    public void testDiscountPrice_percentage() {
        // given
        DiscountType discountType = DiscountType.PERCENTAGE;
        int discountRate = 20; // 20% discount
        Discount discount = new Discount(null, discountType, discountRate);

        int orderPrice = 100;

        //when
        int result = discount.discountPrice(orderPrice);

        //then
        Assertions.assertThat(result).isEqualTo(orderPrice - (orderPrice * discountRate / 100));
    }

    @Test
    public void testDiscountPrice_amount() {
        // given
        DiscountType discountType = DiscountType.AMOUNT;
        int discountRate = 20;
        Discount discount = new Discount(null, discountType, discountRate);

        int orderPrice = 100;

        //when
        int result = discount.discountPrice(orderPrice);

        //then
        Assertions.assertThat(result).isEqualTo(orderPrice - discountRate);
    }

    @Test
    public void testDiscountPrice_negativeResult() {
        // given
        DiscountType discountType = DiscountType.AMOUNT;
        int discountRate = 200;
        Discount discount = new Discount(null, discountType, discountRate);

        int orderPrice = 100;

        //when
        int result = discount.discountPrice(orderPrice);

        //then
        Assertions.assertThat(result).isEqualTo(0);
    }


    @Test
    void existsDiscountItemCheckTest() {
        // given
        Item item = itemFixture.createItem("노트북", 1_000_000);

        // when
        boolean result = discountPolicyValidator.existsDiscountPolicy(item.getId());

        // then
        Assertions.assertThat(result).isFalse();
    }
}
