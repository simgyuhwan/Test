package com.test.cafekiosk.spring.api.controller.order;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.cafekiosk.spring.api.ApiResponse;
import com.test.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import com.test.cafekiosk.spring.api.service.order.OrderService;
import com.test.cafekiosk.spring.api.service.order.response.OrderResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/api/v1/orders/new")
	public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
		LocalDateTime registeredDateTime = LocalDateTime.now();
		return ApiResponse.ok(orderService.createOrder(request.toServiceRequest(), registeredDateTime));
	}
}
