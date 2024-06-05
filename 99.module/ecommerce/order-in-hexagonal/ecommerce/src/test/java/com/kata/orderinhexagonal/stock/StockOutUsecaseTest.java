package com.kata.orderinhexagonal.stock;

import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.fixture.StockFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import com.kata.orderinhexagonal.stock.application.port.in.StockOutUsecase;
import com.kata.orderinhexagonal.stock.domain.Stock;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class StockOutUsecaseTest extends TestConfig {

    @Autowired
    ItemFixture itemFixture;

    @Autowired
    StockFixture stockFixture;

    @Autowired
    StockOutUsecase stockOutUsecase;

    @Test
    void 상품_출고() {
        // given
        Item item = itemFixture.createItem("노트북", 1_000_000);
        int stockOutQuantity = 5;
        int stockInQuantity = 10;
        stockFixture.stockIn(item, stockInQuantity);
        Item stockInItem = itemFixture.getItem(item.getId());
        int currentQuantity = stockInItem.getStockQuantity() - stockOutQuantity;
        StockOutRequest request = StockOutRequest.of(item.getId(), stockOutQuantity);

        // when
        Stock stockOut = stockOutUsecase.stockOut(request);
        // then
        Assertions.assertThat(stockOut.getId()).isPositive();
        Assertions.assertThat(stockOut.getStockType()).isEqualTo(Stock.StockType.STOCK_OUT);
        Assertions.assertThat(stockOut.getQuantity()).isEqualTo(stockOutQuantity);
        Assertions.assertThat(stockOut.getItem().getStockQuantity()).isEqualTo(currentQuantity);
        Assertions.assertThat(stockOut.getItem().getId()).isEqualTo(item.getId());
        Assertions.assertThat(stockOut.getItem().getName()).isEqualTo(item.getName());
    }

}
