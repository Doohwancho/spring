package com.example.productorderservice.playing;

import org.springframework.util.Assert;

record AddFunRequest(String name, int price,
                     FunPolicy funPolicy) {

    AddFunRequest {
        Assert.hasText(name, "상품명 필수!");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야합니다.");
        Assert.notNull(funPolicy, "할인 정책은 필수!");
    }
}
