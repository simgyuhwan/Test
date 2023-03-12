package com.practice.jpa.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품등록() {
        final AddProductRequest request = 상품등록요청_생성();
        productService.addProduct(request);
    }

    private AddProductRequest 상품등록요청_생성() {
        final int price = 1000;
        final String name = "상품명";
        return new AddProductRequest(name, price, DiscountPolicy.NONE);
    }

}
