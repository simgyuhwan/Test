package com.test.cafekiosk.spring.api.service.order.request;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCreateServiceRequest {

	private List<String> productNumbers;

	@Builder
	private OrderCreateServiceRequest(List<String> productNumbers) {
		this.productNumbers = productNumbers;
	}
}
