package com.test.cafekiosk.spring.api.controller.order.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.test.cafekiosk.spring.api.service.order.request.OrderCreateServiceRequest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCreateRequest {

	@NotEmpty(message = "상품 번호 리스트는 필수입니다.")
	private List<String> productNumbers;

	@Builder
	private OrderCreateRequest(List<String> productNumbers) {
		this.productNumbers = productNumbers;
	}

	public OrderCreateServiceRequest toServiceRequest() {
		return OrderCreateServiceRequest.builder()
			.productNumbers(productNumbers)
			.build();
	}
}
