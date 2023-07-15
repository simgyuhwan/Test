package com.test.cafekiosk.spring.api.controller.product;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.test.cafekiosk.spring.api.service.product.ProductService;
import com.test.cafekiosk.spring.domain.product.ProductType;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Controller 에 ProductService MockBean 객체를 주입해줌
	 */
	@MockBean
	private ProductService productService;

	@DisplayName("신규 상품을 등록한다.")
	@Test
	void createProduct() throws Exception {
		// given
		ProductCreateRequest request = ProductCreateRequest.builder()
			.type(ProductType.HANDMADE)
			.name("아메리카노")
			.price(4000)
			.build();

		// when, then
		mockMvc.perform(post("/api/v1/products/new")
				.content(objectMapper.writeValueAsString(request))
				.contentType(APPLICATION_JSON)
			)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

}