package com.practice.jpa.test;

import static java.math.BigDecimal.*;

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

		Integer existingQuantity = basket.getOrDefault(product, 0);
		int newQuantity = existingQuantity + qtyToAdd;
		basket.put(product, newQuantity);

		BigDecimal valueAlreadyInTheCart = product.getPrice()
			.multiply(valueOf(existingQuantity));
		BigDecimal newFinalValueForTheProduct = product.getPrice()
			.multiply(valueOf(newQuantity));

		totalValue = totalValue
			.subtract(valueAlreadyInTheCart)
			.add(newFinalValueForTheProduct);

		assert basket.containsKey(product) : "Product was not inserted in the basket";
		assert totalValue.compareTo(oldTotalValue) > 0 :
			"Total value should be grater than previous total value";
		assert invariant() : "Invariant does not hold";
	}

	public void remove(Product product) {
		assert product != null : "product can't be null";
		assert basket.containsKey(product) : "Product must already be in the basket";

		int qty = basket.get(product);

		BigDecimal productPrice = product.getPrice();
		BigDecimal productTimesQuantity = productPrice.multiply(valueOf(qty));
		totalValue = totalValue.subtract(productTimesQuantity);

		assert !basket.containsKey(product) : "Product is still in the basket";
		assert invariant() : "Invariant does not hold";
	}

	private boolean invariant() {
		return totalValue.compareTo(BigDecimal.ZERO) >= 0;
	}
}
