package com.practice.jpa.test;

public class TaxCalculator {
	public double calculateTax(double value) {
		// 단언문
		assert value >= 0 : "Value cannot be negative";

		if(value < 0) {
			throw new RuntimeException("Value cannot be negative");
		}

		double taxValue = 0;

		// 단언문
		assert taxValue >= 0 : "Calculated tax value cannot be negative";

		if(taxValue < 0) {
			throw new RuntimeException("Calculated tax value cannot be negative");
		}


		return taxValue;
	}
}
