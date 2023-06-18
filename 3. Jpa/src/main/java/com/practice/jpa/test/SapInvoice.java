package com.practice.jpa.test;

import java.util.Objects;

public class SapInvoice {
	private final String customer;
	private final int value;
	private final String id;

	public SapInvoice(String customer, Integer value, String sapId) {
		this.customer = customer;
		this.value = value;
		id = sapId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SapInvoice that = (SapInvoice)o;
		return value == that.value && Objects.equals(customer, that.customer) && Objects.equals(id,
			that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, value, id);
	}
}
