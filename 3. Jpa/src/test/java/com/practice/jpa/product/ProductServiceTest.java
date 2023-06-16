// package com.practice.jpa.product;
//
// import static org.assertj.core.api.Assertions.*;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.ResponseEntity;
//
// @SpringBootTest
// public class ProductServiceTest {
//
//     @Autowired
//     private ProductService productService;
//
//     @Test
//     void 상품수정() {
//         productService.addProduct(ProductSteps.상품등록요청_생성());
//         final Long productId = 1L;
//         final UpdateProductRequest request = 상품수정요청();
//
//         productService.updateProduct(productId, request);
//
//         final ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
//         GetProductResponse productResponse = response.getBody();
//         assertThat(productResponse.name()).isEqualTo("상품 수정");
//         assertThat(productResponse.price()).isEqualTo(2000);
//     }
//
//     private UpdateProductRequest 상품수정요청() {
//         return new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
//     }
//
// }
