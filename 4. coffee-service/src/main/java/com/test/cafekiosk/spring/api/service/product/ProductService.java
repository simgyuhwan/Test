package com.test.cafekiosk.spring.api.service.product;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import com.test.cafekiosk.spring.api.service.product.response.ProductResponse;
import com.test.cafekiosk.spring.domain.product.Product;
import com.test.cafekiosk.spring.domain.product.ProductRepository;
import com.test.cafekiosk.spring.domain.product.ProductSellingStatus;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductNumberFactory productNumberFactory;

	public List<ProductResponse> getSellingProducts() {
		List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

		return products.stream()
				.map(ProductResponse::of)
			.collect(toList());
	}

	@Transactional
	public ProductResponse createProduct(ProductCreateServiceRequest request) {
		String nextProductNumber = productNumberFactory.createNextProductNumber();

		Product product = request.toEntity(nextProductNumber);
		Product savedProduct = productRepository.save(product);

		return ProductResponse.of(savedProduct);
	}

}
