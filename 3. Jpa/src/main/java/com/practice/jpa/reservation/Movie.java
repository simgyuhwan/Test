package com.practice.jpa.reservation;

import java.time.Duration;


public class Movie {
	private String title;
	private Duration runningTime;
	private Money fee;
	private DefaultDiscountPolicy defaultDiscountPolicy;

	public Movie(String title, Duration runningTime, Money fee, DefaultDiscountPolicy defaultDiscountPolicy) {
		this.title = title;
		this.runningTime = runningTime;
		this.fee = fee;
		this.defaultDiscountPolicy = defaultDiscountPolicy;
	}

	public Money getFee() {
		return fee;
	}

	public Money calculateMovieFee(Screening screening) {
		return fee.minus(defaultDiscountPolicy.calculateDiscountAmount(screening));
	}
}
