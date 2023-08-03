package com.test.cafekiosk.spring.domain.product;

import static com.test.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static com.test.cafekiosk.spring.domain.product.ProductType.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.test.cafekiosk.spring.IntegrationTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

/**
 * 반드시 SpringBootTest 를 할 필요도 없고 DataJpaTest 를 할 필요가 없다.
 * 단순히 Jpa 에 관해서 테스트를 하고 싶으면 DataJpaTest 만 사용해도 되지만 테스트 환경이 하나 더 생성되기 때문에
 * SpringBootTest 로 통합해도 된다. 선택의 문제이다.
 */
//@ActiveProfiles("test")
//@DataJpaTest
@Transactional
class ProductRepositoryTest extends IntegrationTestSupport {

	@Autowired
	private ProductRepository productRepository;

	@DisplayName("원하는 판매상태를 가진 상품을 조회한다.")
	@Test
	void findAllBySellingStatusIn() {
		// given
		Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
		Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
		Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
		productRepository.saveAll(List.of(product1, product2, product3));

		// when
		List<Product> products = productRepository.findAllBySellingStatusIn(forDisplay());

		// then
		assertThat(products).containsAnyElementsOf(List.of(product1, product2));
		assertThat(products).hasSize(2)
			.extracting("productNumber", "name", "sellingStatus")
			.containsExactlyInAnyOrder(
				tuple("001", "아메리카노", SELLING),
				tuple("002", "카페라떼", HOLD)
			);
	}

	@DisplayName("상품번호 리스트로 상품을 조회한다.")
	@Test
	void findAllByProductNumberIn() {
		// given
		Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
		Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
		Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
		productRepository.saveAll(List.of(product1, product2, product3));

		// when
		List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));

		// then
		assertThat(products).containsAnyElementsOf(List.of(product1, product2));
		assertThat(products).hasSize(2)
			.extracting("productNumber", "name", "sellingStatus")
			.containsExactlyInAnyOrder(
				tuple("001", "아메리카노", SELLING),
				tuple("002", "카페라떼", HOLD)
			);
	}

	@DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다.")
	@Test
	void findLatestProductNumber() {
		// given
		String targetProductNumber = "003";

		Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
		Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
		Product product3 = createProduct(targetProductNumber, BAKERY, STOP_SELLING, "팥빙수", 7000);
		productRepository.saveAll(List.of(product1, product2, product3));

		// when
		String latestProductNumber = productRepository.findLatestProductNumber();

		// then
		assertThat(latestProductNumber).isEqualTo("003");
	}

	@DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 때, 상품이 하나도 없는 경우에는 null을 반환한다. ")
	@Test
	void findLatestProductNumberWhenProductIsEmpty() {
		// when
		String latestProductNumber = productRepository.findLatestProductNumber();

		// then
		assertThat(latestProductNumber).isNull();
	}

	private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus,
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