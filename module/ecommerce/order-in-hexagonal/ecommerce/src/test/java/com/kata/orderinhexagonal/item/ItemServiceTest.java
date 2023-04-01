package com.kata.orderinhexagonal.item;

import com.kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import com.kata.orderinhexagonal.item.application.service.ItemService;
import com.kata.orderinhexagonal.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void create_item_test() {
        //given
        String name = "item1";
        int price = 100_000_000;
        CreateItemRequest request = new CreateItemRequest(name, price);

        //when
        Item newItem = itemService.createItem(request);

        //then
        Assertions.assertThat(newItem.getId()).isPositive();
        Assertions.assertThat(newItem.getName()).isEqualTo(name);
        Assertions.assertThat(newItem.getPrice()).isEqualTo(price);
        Assertions.assertThat(newItem.getStockQuantity()).isZero();
    }

}
