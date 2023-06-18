package com.practice.jpa.test;

public class Invoice {
	private final String customer;
	private final Integer value;

	public Invoice(String customer, int value) {
		this.customer = customer	;
		this.value = value;
	}

	public String getCustomer() {
		return customer;
	}

	public Integer getValue() {
		return value;
	}
}
