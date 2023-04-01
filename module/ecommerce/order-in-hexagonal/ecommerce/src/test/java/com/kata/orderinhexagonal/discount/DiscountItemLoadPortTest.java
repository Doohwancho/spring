package com.kata.orderinhexagonal.discount;

import com.kata.orderinhexagonal.discount.application.port.out.DiscountItemLoadPort;
import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscountItemLoadPortTest {
    
    @Autowired
    ItemFixture itemFixture;

    @Autowired
    DiscountItemLoadPort discountItemPort;


    @Test 
    void discountItemLoadTest() {
        //given
        Item item = itemFixture.createItem("item", 100);

        //when
        Item loadedItem = discountItemPort.loadItem(item.getId());
        
        //then
        Assertions.assertThat(loadedItem).isNotNull();
        Assertions.assertThat(loadedItem.getId()).isPositive();
        Assertions.assertThat(loadedItem.getName()).isEqualTo(item.getName());
        Assertions.assertThat(loadedItem.getPrice()).isEqualTo(item.getPrice());
    }
}
