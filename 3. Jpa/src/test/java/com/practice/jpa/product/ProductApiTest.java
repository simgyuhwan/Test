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

    @Test
    void 상품조회() {
        productSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        Long productId = 1L;

        final var response = productSteps.상품조회요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }

    private AddProductRequest 상품등록요청_생성() {
        return productSteps.상품등록요청_생성();
    }

}
