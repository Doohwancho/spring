package com.kata.orderinhexagonal.stock;

import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import com.kata.orderinhexagonal.stock.application.service.StockService;
import com.kata.orderinhexagonal.stock.domain.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockServiceTest {

    @Autowired
    ItemFixture itemFixture;
    @Autowired
    StockService stockService;


    @Test 
    void stockInTest() {
        //given
        Item item = itemFixture.createItem("노트북", 1_000_000);
        int quantity = 10;
        StockInRequest request = StockInRequest.of(item.getId(), quantity);

        //when
        Stock stock = stockService.stockIn(request);
        
        //then
        Assertions.assertThat(stock.getId()).isPositive();
        Assertions.assertThat(stock.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(stock.getItem().getId()).isEqualTo(item.getId());
        Assertions.assertThat(stock.getItem().getName()).isEqualTo(item.getName());
        Assertions.assertThat(stock.getItem().getPrice()).isEqualTo(item.getPrice());
        Assertions.assertThat(stock.getItem().getStockQuantity()).isEqualTo(item.getStockQuantity());
    }
}
