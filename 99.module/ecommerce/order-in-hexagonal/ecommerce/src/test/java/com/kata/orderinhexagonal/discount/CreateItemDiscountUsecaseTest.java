package com.kata.orderinhexagonal.discount;

import com.kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountRequest;
import com.kata.orderinhexagonal.discount.domain.Discount;
import com.kata.orderinhexagonal.discount.domain.DiscountType;
import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class CreateItemDiscountUsecaseTest extends TestConfig {

    @Autowired
    CreateItemDiscountUsecase createItemDiscountUsecase;
    @Autowired
    ItemFixture itemFixture;

    @Test
    void createItemDiscountRateTest() {
        // given
        Item item = itemFixture.createItem("노트북", 1_000_000);

        int discountRate = 10;
        DiscountType discountType = DiscountType.PERCENTAGE;
        ItemDiscountRequest request = ItemDiscountRequest.of(item.getId(), discountType, discountRate);
        // when
        Discount discount = createItemDiscountUsecase.applyDiscount(request);

        // then
        Assertions.assertThat(discount.getId()).isPositive();
        Assertions.assertThat(discount.getDiscountType()).isEqualTo(discountType);
        Assertions.assertThat(discount.getDiscountRate()).isEqualTo(discountRate);
        Assertions.assertThat(discount.getItem().getId()).isEqualTo(item.getId());
    }
}
