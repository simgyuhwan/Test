package com.test.cafekiosk.spring.api.controller.product.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.test.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import com.test.cafekiosk.spring.domain.product.Product;
import com.test.cafekiosk.spring.domain.product.ProductSellingStatus;
import com.test.cafekiosk.spring.domain.product.ProductType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCreateRequest {

	@NotNull(message = "상품 타입은 필수입니다.")
	private ProductType type;

	@NotNull(message = "상품 판매상태는 필수입니다.")
	private ProductSellingStatus sellingStatus;

	/**
	 * 기본적으로 문자가 있고 없고는 컨트롤러에서 검증을 하는 것이 좋고
	 * 글자 수 제한같은 좀 더 상셍한 validation 은 서비스 계층에서 하는게 좋다.
	 */
	@NotBlank(message = "상품 이름은 필수입니다.")
	private String name;

	@Positive(message = "상품 가격은 양수여야 합니다.")
	private int price;

	@Builder
	private ProductCreateRequest(ProductType type,
		ProductSellingStatus sellingStatus, String name, int price) {
		this.type = type;
		this.sellingStatus = sellingStatus;
		this.name = name;
		this.price = price;
	}

	public Product toEntity(String nextProductNumber) {
		return Product.builder()
			.productNumber(nextProductNumber)
			.type(type)
			.sellingStatus(sellingStatus)
			.name(name)
			.price(price)
			.build();
	}

	public ProductCreateServiceRequest toServiceRequest() {
		return ProductCreateServiceRequest.builder()
			.name(name)
			.type(type)
			.sellingStatus(sellingStatus)
			.price(price)
			.build();
	}
}
