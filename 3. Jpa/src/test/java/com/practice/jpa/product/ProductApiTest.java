package com.practice.jpa.product;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest{

    private final ProductSteps productSteps = new ProductSteps();

    @Test
    void 상품등록() {
        final var request = productSteps.상품등록요청_생성();
        // API 요청
        final var response = ProductSteps.상품등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private AddProductRequest 상품등록요청_생성() {
        return productSteps.상품등록요청_생성();
    }

}
