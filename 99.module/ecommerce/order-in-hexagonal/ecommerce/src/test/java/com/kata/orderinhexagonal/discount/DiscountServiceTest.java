package com.kata.orderinhexagonal.discount;

import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountRequest;
import com.kata.orderinhexagonal.discount.application.service.DiscountService;
import com.kata.orderinhexagonal.discount.domain.Discount;
import com.kata.orderinhexagonal.discount.domain.DiscountType;
import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class DiscountServiceTest extends TestConfig {

    @Autowired
    ItemFixture itemFixture;

    @Autowired
    DiscountService discountService;

    @Test
    void item_discount_test() {
        //given
        String name = "item";
        int price = 100;
        Item item = itemFixture.createItem(name, price);

        DiscountType discountType = DiscountType.PERCENTAGE;
        int discountRate = 10;
        ItemDiscountRequest request = new ItemDiscountRequest(item.getId(), discountType, discountRate);

        //when
        Discount result = discountService.applyDiscount(request);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getItem().getName()).isEqualTo(name);
        Assertions.assertThat(result.getItem().getPrice()).isEqualTo(price);
        Assertions.assertThat(result.getDiscountType()).isEqualTo(discountType);
        Assertions.assertThat(result.getDiscountRate()).isEqualTo(discountRate);
    }
}
