package com.practice.jpa.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {
	private BigDecimal totalValue = BigDecimal.ZERO;
	private Map<Product, Integer> basket = new HashMap<>();

	public void add(Product product, int qtyToAdd) {
		assert product != null : "Product is required";
		assert qtyToAdd > 0 : "Quantity has to be greater than zero";
		BigDecimal oldTotalValue = totalValue;

		assert basket.containsKey(product) : "Product was not inserted in the basket";
		assert totalValue.compareTo(oldTotalValue) > 0 :
			"Total value should be grater than previous total value";
		assert invariant() : "Invariant does not hold";
	}

	public void remove(Product product) {
		assert product != null : "product can't be null";
		assert basket.containsKey(product) : "Product must already be in the basket";

		assert !basket.containsKey(product) : "Product is still in the basket";
		assert invariant() : "Invariant does not hold";
	}

	private boolean invariant() {
		return totalValue.compareTo(BigDecimal.ZERO) >= 0;
	}
}
