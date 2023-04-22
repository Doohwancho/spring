package com.kata.orderinhexagonal.stock;

import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import com.kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import com.kata.orderinhexagonal.stock.domain.Stock;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class StockInUsecaseTest extends TestConfig {

    @Autowired
    ItemFixture itemFixture;
    @Autowired
    StockInUsecase stockInUsecase;

    @Test
    void 상품_입고() {
        // given
        Item item = itemFixture.createItem("노트북", 1_000_000);
        int quantity = 1;
        StockInRequest request = StockInRequest.of(item.getId(), quantity);

        // when
        Stock stock = stockInUsecase.stockIn(request);
        // then
        Assertions.assertThat(stock.getId()).isPositive();
        Assertions.assertThat(stock.getQuantity()).isEqualTo(quantity);
        Item stockInItem = stock.getItem();
        Assertions.assertThat(stockInItem.getStockQuantity()).isEqualTo(quantity);
        Assertions.assertThat(stockInItem.getId()).isEqualTo(item.getId());
        Assertions.assertThat(stockInItem.getName()).isEqualTo(item.getName());
    }

}
