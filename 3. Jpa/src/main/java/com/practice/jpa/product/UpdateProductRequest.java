package com.practice.jpa.product;

import org.springframework.util.Assert;

record UpdateProductRequest(String name, int price, DiscountPolicy discountPolicy) {
    UpdateProductRequest {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
    }
}
