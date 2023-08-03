package com.test.cafekiosk.spring.api.service.product;

import static com.test.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static com.test.cafekiosk.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.test.cafekiosk.spring.IntegrationTestSupport;
import com.test.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.test.cafekiosk.spring.api.service.product.response.ProductResponse;
import com.test.cafekiosk.spring.domain.product.Product;
import com.test.cafekiosk.spring.domain.product.ProductRepository;
import com.test.cafekiosk.spring.domain.product.ProductSellingStatus;
import com.test.cafekiosk.spring.domain.product.ProductType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @BeforeEach
    void setUp() {
        // 각 테스트 입장에서 봤을 때 : 아예 몰라도 테스트 내용을 이해하는 데에 문제가 없는가?
        // 수정해도 모든 테스트에 영향을 주지 않는가?
    }

    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다.")
    @Test
    void createProduct() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product1);

        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        // when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingType", "name", "price")
            .contains("002", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .containsExactlyInAnyOrder(
                tuple("001", HANDMADE, SELLING, "아메리카노", 4000),
                tuple("002", HANDMADE, SELLING, "카푸치노", 5000)
            );
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    void createProductWhenProductIsEmpty() {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        // when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingType", "name", "price")
            .contains("001", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains(tuple("001", HANDMADE, SELLING, "카푸치노", 5000));
    }

    private Product createProduct(String productNumber, ProductType type,
        ProductSellingStatus sellingStatus,
        String name, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(type)
            .sellingStatus(sellingStatus)
            .name(name)
            .price(price)
            .build();
    }

}