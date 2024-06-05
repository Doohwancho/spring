package com.kata.orderinhexagonal.stock;

import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import com.kata.orderinhexagonal.stock.domain.Stock;
import com.kata.orderinhexagonal.stock.domain.StockIn;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class SaveStockPortTest extends TestConfig {

    @Autowired
    ItemFixture itemFixture;
    @Autowired
    SaveStockPort saveStockPort;

    @Test
    void saveStockTest() {
        // given
        Item item = itemFixture.createItem("노트북", 1_000_000);
        int quantity = 1;
        Stock stock = new StockIn(quantity, item);
        // when
        saveStockPort.save(stock);
        // then
        Assertions.assertThat(stock.getId()).isPositive();
        Assertions.assertThat(stock.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(stock.getStockType()).isEqualTo(Stock.StockType.STOCK_IN);
    }

}
