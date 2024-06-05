package com.kata.orderinhexagonal.stock;


import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class LoadItemPortTest extends TestConfig {

    @Autowired
    ItemFixture itemFixture;
    @Autowired
    LoadItemPort loadItemPort;

    @Test
    void loadItemTest() {
        // given
        Item item = itemFixture.createItem("노트북", 1_000_000);
        // when
        Item loadItem = loadItemPort.load(item.getId());
        // then
        Assertions.assertThat(loadItem.getId()).isEqualTo(item.getId());
        Assertions.assertThat(loadItem.getName()).isEqualTo(item.getName());
        Assertions.assertThat(loadItem.getPrice()).isEqualTo(item.getPrice());
        Assertions.assertThat(loadItem.getStockQuantity()).isEqualTo(item.getStockQuantity());
    }

    @Test
    void loadItemEntityTest() {
        // given
        Item item = itemFixture.createItem("노트북", 1_000_000);
        // when
        ItemEntity loadItemEntity = loadItemPort.loadItemEntity(item.getId());
        // then
        Assertions.assertThat(loadItemEntity.getId()).isEqualTo(item.getId());
        Assertions.assertThat(loadItemEntity.getName()).isEqualTo(item.getName());
        Assertions.assertThat(loadItemEntity.getPrice()).isEqualTo(item.getPrice());
        Assertions.assertThat(loadItemEntity.getStockQuantity()).isEqualTo(item.getStockQuantity());
    }


}
