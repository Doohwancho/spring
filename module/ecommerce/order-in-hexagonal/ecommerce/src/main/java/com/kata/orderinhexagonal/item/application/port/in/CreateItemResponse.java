package com.kata.orderinhexagonal.item.application.port.in;

import com.kata.orderinhexagonal.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemResponse {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public CreateItemResponse(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
    }
}
