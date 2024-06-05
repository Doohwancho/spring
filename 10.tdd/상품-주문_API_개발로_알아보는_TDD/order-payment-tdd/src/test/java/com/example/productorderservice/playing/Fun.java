package com.example.productorderservice.playing;

import org.springframework.util.Assert;

class Fun {
    private Long id;
    private final String name;
    private final int price;
    private final FunPolicy funPolicy;

    public Fun(String name, int price, FunPolicy funPolicy) {
        Assert.hasText(name, "상품명 필수!");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야합니다.");
        Assert.notNull(funPolicy, "할인 정책은 필수!");
        this.name = name;
        this.price = price;
        this.funPolicy = funPolicy;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
