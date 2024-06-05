package com.kata.orderinhexagonal.item.adapter.out.persistence;

import com.kata.orderinhexagonal.discount.adapter.out.persistence.DiscountEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    int stockQuantity;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY) //ItemRepository에 findItemAndDiscountFetchById()에서 discount table과 조인하기 때문에 필요.
    private DiscountEntity discount; //TODO - c-b-6-1. 원래 discount는 item에 속하지 않지만, repository에서 item을 가져올 때 discount를 가져오기 위해 임시로 있는 필드.


    public ItemEntity(long id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public ItemEntity(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void changeStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
