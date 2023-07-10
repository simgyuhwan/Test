package com.test.cafekiosk.spring.domain.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingTypes);

	List<Product> findAllByProductNumberIn(List<String> productNumbers);

	@Query(value = "SELECT p.product_number FROM product p ORDER BY p.id DESC LIMIT 1", nativeQuery = true)
	String findLatestProductNumber();
}
