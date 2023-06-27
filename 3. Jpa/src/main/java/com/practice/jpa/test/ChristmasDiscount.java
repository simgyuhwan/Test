package com.practice.jpa.test;

import java.time.LocalDate;
import java.time.Month;

/**
 * 크리스마스일 때, 할인 적용
 */
public class ChristmasDiscount {
	private Clock clock;

	public ChristmasDiscount(Clock clock) {
		this.clock = clock;
	}

	public double applyDiscount(double amount) {
		LocalDate today = clock.now();

		double discountPercentage = 0;
		boolean isChristmas = today.getMonth() == Month.DECEMBER && today.getDayOfMonth() == 25;

		if(isChristmas)
			discountPercentage = 0.15;

		return amount - (amount * discountPercentage);
	}
}
